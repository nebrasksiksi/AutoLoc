package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClientDTO(
        Long idClient,
        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 50, message = "Le nom ne peut pas dépasser 50 caractères") String nom,
        @NotBlank(message = "Le prénom est obligatoire")
        @Size(max = 50, message = "Le prénom ne peut pas dépasser 50 caractères") String prenom,
        @NotBlank(message = "L'adresse e-mail est obligatoire")
        @Size(max = 100, message = "L'adresse e-mail ne peut pas dépasser 100 caractères")
        @Email(message = "L'adresse e-mail est invalide") String email,
        @Size(max = 20, message = "Le téléphone ne peut pas dépasser 20 caractères") String telephone,
        @NotBlank(message = "Le numéro de permis est obligatoire")
        @Size(max = 30, message = "Le permis ne peut pas dépasser 30 caractères") String numPermis,
        @NotNull(message = "La date d'inscription est obligatoire") LocalDate dateInscription) {
}
