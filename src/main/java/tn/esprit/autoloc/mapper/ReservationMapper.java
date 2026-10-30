package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.ReservationDTO;

@Component
public class ReservationMapper {
    public ReservationDTO toDTO(Reservation reservation) {
        return reservation == null ? null : new ReservationDTO(
                reservation.getIdReservation(),
                reservation.getVehicule() == null ? null : reservation.getVehicule().getIdVehicule(),
                reservation.getClient() == null ? null : reservation.getClient().getIdClient(),
                reservation.getDateDebut(), reservation.getDateFin(), reservation.getStatut());
    }

    public Reservation toEntity(ReservationDTO dto) {
        if (dto == null) return null;
        Reservation reservation = new Reservation();
        reservation.setIdReservation(dto.idReservation());
        reservation.setDateDebut(dto.dateDebut());
        reservation.setDateFin(dto.dateFin());
        reservation.setStatut(dto.statut());
        if (dto.vehiculeId() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.vehiculeId());
            reservation.setVehicule(vehicule);
        }
        if (dto.clientId() != null) {
            Client client = new Client();
            client.setIdClient(dto.clientId());
            reservation.setClient(client);
        }
        return reservation;
    }
}
