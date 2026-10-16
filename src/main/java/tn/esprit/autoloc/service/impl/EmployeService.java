
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;
import tn.esprit.autoloc.service.IEmployeService;

@Service
public class EmployeService extends CrudServiceImpl<Employe>
        implements IEmployeService {

    public EmployeService(IEmployeRepository repository) {
        super(repository);
    }
}
