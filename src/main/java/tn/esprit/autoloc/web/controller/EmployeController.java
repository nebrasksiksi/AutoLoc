
package tn.esprit.autoloc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;
import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {
    private final IEmployeService service;

    public EmployeController(IEmployeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employe> ajouter(@RequestBody Employe e) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.ajouter(e));
    }

    @GetMapping
    public List<Employe> afficherTous() {
        return service.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> chercherParId(
            @PathVariable Long id) {
        return service.chercherParId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public Employe modifier(@RequestBody Employe e) {
        return service.modifier(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
