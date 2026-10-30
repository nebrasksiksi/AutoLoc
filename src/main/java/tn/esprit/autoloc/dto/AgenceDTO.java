package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AgenceDTO(
        Long idAgence,
        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères") String nom,
        @NotBlank(message = "La ville est obligatoire")
        @Size(max = 100, message = "La ville ne peut pas dépasser 100 caractères") String ville,
        @Size(max = 255, message = "L'adresse ne peut pas dépasser 255 caractères") String adresse,
        @Size(max = 20, message = "Le téléphone ne peut pas dépasser 20 caractères") String telephone) {
}
