
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.service.IAgenceService;

@Service
public class AgenceService extends CrudServiceImpl<Agence>
        implements IAgenceService {

    public AgenceService(IAgenceRepository repository) {
        super(repository);
    }
}
