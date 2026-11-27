
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratDTO;

import java.time.LocalDate;

public interface IContratService extends ICrudService<Contrat> {
    ContratDTO affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin, Long idReservation);
}
