
package tn.esprit.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client ajouter(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> afficherTous() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> chercherParId(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client modifier(Long id, Client client) {
        Client existant = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Client introuvable : " + id));

        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());

        return clientRepository.save(existant);
    }

    @Override
    public void supprimer(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Client introuvable : " + id);
        }

        clientRepository.deleteById(id);
    }
}
