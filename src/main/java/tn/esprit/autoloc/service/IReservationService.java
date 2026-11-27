
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.domain.Reservation;

public interface IReservationService
        extends ICrudService<Reservation> {
    ReservationResponseDTO affecterReservationAClient(Long idReservation, String nom, String prenom);
    ReservationResponseDTO creerReservationEtContratAssocie(ReservationWithContratRequestDTO requestDTO);
}
