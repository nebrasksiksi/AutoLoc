
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Service
@Transactional
public class AgenceService implements IAgenceService {

    private final IAgenceRepository repository;

    public AgenceService(IAgenceRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agence> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Agence getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Agence introuvable"));
    }

    @Override
    public Agence create(Agence agence) {
        return repository.save(agence);
    }

    @Override
    public Agence update(Long id, Agence agence) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Agence introuvable");
        }
        agence.setIdAgence(id);
        return repository.save(agence);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Agence introuvable");
        }
        repository.deleteById(id);
    }
}
