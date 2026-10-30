package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import tn.esprit.autoloc.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaiementDTO(
        Long idPaiement,
        @NotNull(message = "Le contrat est obligatoire")
        @Positive(message = "L'identifiant du contrat doit être positif") Long contratId,
        @NotNull(message = "Le montant est obligatoire")
        @Positive(message = "Le montant doit être supérieur à zéro")
        BigDecimal montant,
        @NotNull(message = "La date de paiement est obligatoire") LocalDate datePaiement,
        @NotNull(message = "Le mode de paiement est obligatoire") ModePaiement modePaiement) {
}
