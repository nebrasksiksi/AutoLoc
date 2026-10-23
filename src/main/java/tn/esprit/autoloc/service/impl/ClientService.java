
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Service
@Transactional
public class ClientService implements IClientService {

    private final IClientRepository repository;

    public ClientService(IClientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Client introuvable"));
    }

    @Override
    public Client create(Client client) {
        return repository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        Client existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Client introuvable"));

        client.setIdClient(id);

        return repository.save(client);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Client introuvable");
        }
        repository.deleteById(id);
    }
}
