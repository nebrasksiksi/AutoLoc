package tn.esprit.autoloc;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.dto.ClientDTO;
import tn.esprit.autoloc.dto.ReservationDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DtoValidationTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void rejectsInvalidClientFields() {
        ClientDTO dto = new ClientDTO(null, "", "Martin", "email-invalide",
                null, "", null);

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("nom")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("numPermis")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dateInscription")));
    }

    @Test
    void rejectsReservationWithEndDateBeforeStartDate() {
        ReservationDTO dto = new ReservationDTO(1L, 2L, 3L,
                LocalDate.of(2026, 10, 30), LocalDate.of(2026, 10, 29),
                StatutReservation.EN_ATTENTE);

        assertTrue(validator.validate(dto).stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("dateRangeValid")));
    }
}
