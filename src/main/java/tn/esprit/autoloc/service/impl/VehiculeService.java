
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.service.IVehiculeService;

@Service
public class VehiculeService extends CrudServiceImpl<Vehicule>
        implements IVehiculeService {

    public VehiculeService(IVehiculeRepository repository) {
        super(repository);
    }
}
