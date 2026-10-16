
package tn.esprit.autoloc.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {

    T ajouter(T entity);

    List<T> afficherTous();

    Optional<T> chercherParId(Long id);

    T modifier(T entity);

    void supprimer(Long id);
}
