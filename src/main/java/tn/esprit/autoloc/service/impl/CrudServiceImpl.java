
package tn.esprit.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.service.ICrudService;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class CrudServiceImpl<T>
        implements ICrudService<T> {

    protected final JpaRepository<T, Long> repository;

    protected CrudServiceImpl(
            JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public T ajouter(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> afficherTous() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> chercherParId(Long id) {
        return repository.findById(id);
    }

    @Override
    public T modifier(T entity) {
        return repository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Entité introuvable : " + id);
        }

        repository.deleteById(id);
    }
}
