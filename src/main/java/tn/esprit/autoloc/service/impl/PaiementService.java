
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;
import tn.esprit.autoloc.service.IPaiementService;

@Service
public class PaiementService extends CrudServiceImpl<Paiement>
        implements IPaiementService {

    public PaiementService(IPaiementRepository repository) {
        super(repository);
    }
}
