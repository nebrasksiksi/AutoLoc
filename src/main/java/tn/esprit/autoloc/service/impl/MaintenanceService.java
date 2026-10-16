
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;
import tn.esprit.autoloc.service.IMaintenanceService;

@Service
public class MaintenanceService
        extends CrudServiceImpl<Maintenance>
        implements IMaintenanceService {

    public MaintenanceService(IMaintenanceRepository repository) {
        super(repository);
    }
}
