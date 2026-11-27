package tn.esprit.autoloc.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import tn.esprit.autoloc.domain.StatutReservation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationWithContratRequestDTO(
        @Valid ReservationDTO reservation,
        @Valid ContratDTO contrat,
        Long vehiculeId,
        Long clientId,
        LocalDate dateDebut,
        LocalDate dateFin,
        StatutReservation statut,
        LocalDate dateSignature,
        BigDecimal montantTotal,
        Boolean valide) {

    public ReservationWithContratRequestDTO(ReservationDTO reservation, ContratDTO contrat) {
        this(reservation, contrat, reservation == null ? null : reservation.vehiculeId(),
                reservation == null ? null : reservation.clientId(),
                reservation == null ? null : reservation.dateDebut(),
                reservation == null ? null : reservation.dateFin(),
                reservation == null ? null : reservation.statut(),
                contrat == null ? null : contrat.dateSignature(),
                contrat == null ? null : contrat.montantTotal(),
                contrat == null ? null : contrat.valide());
    }
}
