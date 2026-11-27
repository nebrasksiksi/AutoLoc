
package tn.esprit.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.service.IContratService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class ContratService extends CrudServiceImpl<Contrat>
        implements IContratService {

    private final IContratRepository contratRepository;
    private final IReservationRepository reservationRepository;

    public ContratService(IContratRepository contratRepository,
                          IReservationRepository reservationRepository) {
        super(contratRepository);
        this.contratRepository = contratRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ContratDTO affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin, Long idReservation) {
        if (dateSignature == null || dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Les dates sont obligatoires");
        }
        if (dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être postérieure ou égale à la date de début");
        }

        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Réservation introuvable pour cette période"));

        Contrat contrat = reservation.getContrat();
        if (contrat == null) {
            contrat = new Contrat();
            contrat.setReservation(reservation);
        }
        contrat.setDateSignature(dateSignature);
        contrat.setValide(true);

        long days = Math.max(1, ChronoUnit.DAYS.between(dateDebut, dateFin) + 1);
        BigDecimal tarif = reservation.getVehicule() == null || reservation.getVehicule().getTarifJournalier() == null
                ? BigDecimal.ZERO
                : reservation.getVehicule().getTarifJournalier();
        contrat.setMontantTotal(tarif.multiply(BigDecimal.valueOf(days)));

        contrat = contratRepository.save(contrat);
        reservation.setContrat(contrat);
        return new ContratDTO(contrat.getIdContrat(), reservation.getIdReservation(),
                contrat.getDateSignature(), contrat.getMontantTotal(), contrat.isValide());
    }
}
