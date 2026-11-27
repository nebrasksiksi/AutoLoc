
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.mapper.VehiculeMapper;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IMaintenanceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.service.IVehiculeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehiculeService implements IVehiculeService {

    private final IVehiculeRepository repository;
    private final IEquipementRepository equipementRepository;
    private final IMaintenanceRepository maintenanceRepository;
    private final VehiculeMapper mapper;

    public VehiculeService(IVehiculeRepository repository,
                           IEquipementRepository equipementRepository,
                           IMaintenanceRepository maintenanceRepository,
                           VehiculeMapper mapper) {
        this.repository = repository;
        this.equipementRepository = equipementRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.mapper = mapper;
    }

    @Override
    public VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = repository.findById(idVehicule)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Véhicule introuvable"));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Équipement introuvable"));
        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new ArrayList<>());
        }
        if (!vehicule.getEquipements().contains(equipement)) {
            vehicule.getEquipements().add(equipement);
        }
        return mapper.toDTO(repository.save(vehicule));
    }

    @Override
    public VehiculeDTO affecterMaintenanceAvehicule(String description, String immatriculation) {
        Vehicule vehicule = repository.findByImmatriculation(immatriculation)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Véhicule introuvable"));
        LocalDate today = LocalDate.now();
        Maintenance maintenance = new Maintenance();
        maintenance.setVehicule(vehicule);
        maintenance.setDescription(description);
        maintenance.setDateDebut(today);
        maintenance.setDateFin(today);
        maintenanceRepository.save(maintenance);
        return mapper.toDTO(vehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicule getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicule introuvable"));
    }

    @Override
    public Vehicule create(Vehicule vehicule) {
        return repository.save(vehicule);
    }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Vehicule introuvable");
        }
        vehicule.setIdVehicule(id);
        return repository.save(vehicule);
    }




    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Vehicule introuvable");
        }
        repository.deleteById(id);
    }
}
