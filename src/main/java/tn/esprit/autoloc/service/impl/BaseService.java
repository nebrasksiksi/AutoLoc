
package tn.esprit.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<T> {

    protected final JpaRepository<T, Long> repository;

    protected BaseService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T ajouter(T entity) {
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<T> afficherTous() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<T> chercherParId(Long id) {
        return repository.findById(id);
    }

    public void supprimer(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Entité introuvable : " + id);
        }
        repository.deleteById(id);
    }
}
