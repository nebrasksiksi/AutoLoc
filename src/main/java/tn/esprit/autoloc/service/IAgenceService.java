
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;
import java.util.List;
import java.util.Optional;

public interface IAgenceService {

    Agence ajouter(Agence agence);

    List<Agence> afficherTous();

    Optional<Agence> chercherParId(Long id);

    Agence modifier(Long id, Agence agence);

    void supprimer(Long id);
}
