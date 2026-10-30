package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MaintenanceDTO(
        Long idMaintenance,
        @NotNull(message = "Le véhicule est obligatoire")
        @Positive(message = "L'identifiant du véhicule doit être positif") Long vehiculeId,
        @NotNull(message = "La date de début est obligatoire") LocalDate dateDebut,
        @NotNull(message = "La date de fin est obligatoire") LocalDate dateFin,
        @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
        String description) {

    @AssertTrue(message = "La date de fin doit être postérieure ou égale à la date de début")
    public boolean isDateRangeValid() {
        return dateDebut == null || dateFin == null || !dateFin.isBefore(dateDebut);
    }
}
