package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.MaintenanceDTO;

@Component
public class MaintenanceMapper {
    public MaintenanceDTO toDTO(Maintenance maintenance) {
        return maintenance == null ? null : new MaintenanceDTO(
                maintenance.getIdMaintenance(),
                maintenance.getVehicule() == null ? null : maintenance.getVehicule().getIdVehicule(),
                maintenance.getDateDebut(), maintenance.getDateFin(),
                maintenance.getDescription());
    }

    public Maintenance toEntity(MaintenanceDTO dto) {
        if (dto == null) return null;
        Maintenance maintenance = new Maintenance();
        maintenance.setIdMaintenance(dto.idMaintenance());
        maintenance.setDateDebut(dto.dateDebut());
        maintenance.setDateFin(dto.dateFin());
        maintenance.setDescription(dto.description());
        if (dto.vehiculeId() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.vehiculeId());
            maintenance.setVehicule(vehicule);
        }
        return maintenance;
    }
}
