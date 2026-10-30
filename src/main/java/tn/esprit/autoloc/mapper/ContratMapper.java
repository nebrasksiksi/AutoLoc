package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.dto.ContratDTO;

@Component
public class ContratMapper {
    public ContratDTO toDTO(Contrat contrat) {
        return contrat == null ? null : new ContratDTO(
                contrat.getIdContrat(),
                contrat.getReservation() == null ? null : contrat.getReservation().getIdReservation(),
                contrat.getDateSignature(), contrat.getMontantTotal(), contrat.isValide());
    }

    public Contrat toEntity(ContratDTO dto) {
        if (dto == null) return null;
        Contrat contrat = new Contrat();
        contrat.setIdContrat(dto.idContrat());
        contrat.setDateSignature(dto.dateSignature());
        contrat.setMontantTotal(dto.montantTotal());
        contrat.setValide(dto.valide());
        if (dto.reservationId() != null) {
            Reservation reservation = new Reservation();
            reservation.setIdReservation(dto.reservationId());
            contrat.setReservation(reservation);
        }
        return contrat;
    }
}
