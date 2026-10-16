
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.service.IEquipementService;

@Service
public class EquipementService
        extends CrudServiceImpl<Equipement>
        implements IEquipementService {

    public EquipementService(IEquipementRepository repository) {
        super(repository);
    }
}
