
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Service
@Transactional
public class VehiculeService implements IVehiculeService {

    private final IVehiculeRepository repository;

    public VehiculeService(IVehiculeRepository repository) {
        this.repository = repository;
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
