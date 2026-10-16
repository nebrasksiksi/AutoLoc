
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.service.IClientService;

@Service
public class ClientService extends CrudServiceImpl<Client>
        implements IClientService {

    public ClientService(IClientRepository repository) {
        super(repository);
    }
}
