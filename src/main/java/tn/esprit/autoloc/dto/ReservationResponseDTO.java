package tn.esprit.autoloc.dto;

import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;

public record ReservationResponseDTO(
        Long idReservation,
        Long vehiculeId,
        Long clientId,
        LocalDate dateDebut,
        LocalDate dateFin,
        StatutReservation statut,
        ContratDTO contrat) {

    public Long reservationId() {
        return idReservation;
    }
}
