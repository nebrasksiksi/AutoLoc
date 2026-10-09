
package tn.esprit.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgenceService implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    public AgenceService(IAgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public Agence ajouter(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agence> afficherTous() {
        return agenceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Agence> chercherParId(Long id) {
        return agenceRepository.findById(id);
    }

    @Override
    public Agence modifier(Long id, Agence agence) {
        Agence existante = agenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Agence introuvable : " + id));

        existante.setNom(agence.getNom());
        existante.setVille(agence.getVille());
        existante.setAdresse(agence.getAdresse());
        existante.setTelephone(agence.getTelephone());

        return agenceRepository.save(existante);
    }

    @Override
    public void supprimer(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Agence introuvable : " + id);
        }

        agenceRepository.deleteById(id);
    }
}
