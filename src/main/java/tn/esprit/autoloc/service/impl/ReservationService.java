
package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.service.IReservationService;

@Service
public class ReservationService
        extends CrudServiceImpl<Reservation>
        implements IReservationService {

    public ReservationService(IReservationRepository repository) {
        super(repository);
    }
}
