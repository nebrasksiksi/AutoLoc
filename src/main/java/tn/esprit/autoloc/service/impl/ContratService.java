
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.service.IContratService;

@Service
public class ContratService extends CrudServiceImpl<Contrat>
        implements IContratService {

    public ContratService(IContratRepository repository) {
        super(repository);
    }
}
