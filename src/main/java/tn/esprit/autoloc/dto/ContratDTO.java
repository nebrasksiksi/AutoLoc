package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratDTO(
        Long idContrat,
        @NotNull(message = "La réservation est obligatoire")
        @Positive(message = "L'identifiant de la réservation doit être positif") Long reservationId,
        @NotNull(message = "La date de signature est obligatoire") LocalDate dateSignature,
        @NotNull(message = "Le montant total est obligatoire")
        @Positive(message = "Le montant total doit être supérieur à zéro")
        BigDecimal montantTotal,
        boolean valide) {
}
