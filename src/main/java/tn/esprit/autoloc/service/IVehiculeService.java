
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeDTO;

import java.util.List;

public interface IVehiculeService {
    VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement);
    VehiculeDTO affecterMaintenanceAvehicule(String description, String immatriculation);

    List<Vehicule> getAll();
    Vehicule getById(Long id);
    Vehicule create(Vehicule vehicule);
    Vehicule update(Long id, Vehicule vehicule);
    void delete(Long id);
}
