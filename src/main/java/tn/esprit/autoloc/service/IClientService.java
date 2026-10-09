
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;
import java.util.List;
import java.util.Optional;

public interface IClientService {

    Client ajouter(Client client);

    List<Client> afficherTous();

    Optional<Client> chercherParId(Long id);

    Client modifier(Long id, Client client);

    void supprimer(Long id);
}
