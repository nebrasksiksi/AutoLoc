
package tn.esprit.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.ReservationDTO;
import tn.esprit.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.service.IReservationService;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class ReservationService
        extends CrudServiceImpl<Reservation>
        implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final IClientRepository clientRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final IContratRepository contratRepository;

    public ReservationService(IReservationRepository reservationRepository,
                              IClientRepository clientRepository,
                              IVehiculeRepository vehiculeRepository,
                              IContratRepository contratRepository) {
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.contratRepository = contratRepository;
    }

    @Override
    public ReservationResponseDTO affecterReservationAClient(Long idReservation, String nom, String prenom) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation introuvable : " + idReservation));
        Client client = clientRepository.findByNomAndPrenom(nom, prenom)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + nom + " " + prenom));
        reservation.setClient(client);
        return toResponse(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponseDTO creerReservationEtContratAssocie(ReservationWithContratRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("La requête est obligatoire");
        }

        Reservation reservation = new Reservation();
        ReservationDTO reservationDTO = requestDTO.reservation();
        if (reservationDTO != null) {
            reservation.setDateDebut(reservationDTO.dateDebut());
            reservation.setDateFin(reservationDTO.dateFin());
            reservation.setStatut(reservationDTO.statut());
            if (reservationDTO.vehiculeId() != null) {
                reservation.setVehicule(vehiculeRepository.findById(reservationDTO.vehiculeId())
                        .orElseThrow(() -> new EntityNotFoundException("Véhicule introuvable : " + reservationDTO.vehiculeId())));
            }
            if (reservationDTO.clientId() != null) {
                reservation.setClient(clientRepository.findById(reservationDTO.clientId())
                        .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + reservationDTO.clientId())));
            }
        } else {
            reservation.setDateDebut(requestDTO.dateDebut());
            reservation.setDateFin(requestDTO.dateFin());
            reservation.setStatut(requestDTO.statut() == null ? StatutReservation.EN_ATTENTE : requestDTO.statut());
            if (requestDTO.vehiculeId() != null) {
                reservation.setVehicule(vehiculeRepository.findById(requestDTO.vehiculeId())
                        .orElseThrow(() -> new EntityNotFoundException("Véhicule introuvable : " + requestDTO.vehiculeId())));
            }
            if (requestDTO.clientId() != null) {
                reservation.setClient(clientRepository.findById(requestDTO.clientId())
                        .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + requestDTO.clientId())));
            }
        }

        if (reservation.getDateDebut() == null || reservation.getDateFin() == null) {
            throw new IllegalArgumentException("Les dates de début et de fin sont obligatoires");
        }
        if (reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin doit être postérieure ou égale à la date de début");
        }

        reservation = reservationRepository.save(reservation);

        Contrat contrat = new Contrat();
        ContratDTO contratDTO = requestDTO.contrat();
        if (contratDTO != null) {
            contrat.setDateSignature(contratDTO.dateSignature());
            contrat.setMontantTotal(contratDTO.montantTotal());
            contrat.setValide(contratDTO.valide());
        } else {
            contrat.setDateSignature(requestDTO.dateSignature());
            contrat.setMontantTotal(requestDTO.montantTotal());
            contrat.setValide(requestDTO.valide() == null || requestDTO.valide());
        }
        if (contrat.getDateSignature() == null) {
            contrat.setDateSignature(reservation.getDateDebut());
        }
        if (contrat.getMontantTotal() == null) {
            long days = Math.max(1, ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin()) + 1);
            BigDecimal tariff = reservation.getVehicule() == null ? BigDecimal.ZERO : reservation.getVehicule().getTarifJournalier();
            contrat.setMontantTotal(tariff == null ? BigDecimal.ZERO : tariff.multiply(BigDecimal.valueOf(days)));
        }
        contrat.setReservation(reservation);
        contrat = contratRepository.save(contrat);
        reservation.setContrat(contrat);

        return toResponse(reservation);
    }

    private ReservationResponseDTO toResponse(Reservation reservation) {
        Contrat contrat = reservation.getContrat();
        ContratDTO contratDTO = contrat == null ? null : new ContratDTO(
                contrat.getIdContrat(), reservation.getIdReservation(), contrat.getDateSignature(),
                contrat.getMontantTotal(), contrat.isValide());
        return new ReservationResponseDTO(
                reservation.getIdReservation(),
                reservation.getVehicule() == null ? null : reservation.getVehicule().getIdVehicule(),
                reservation.getClient() == null ? null : reservation.getClient().getIdClient(),
                reservation.getDateDebut(), reservation.getDateFin(), reservation.getStatut(), contratDTO);
    }
}
