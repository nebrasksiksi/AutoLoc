
package tn.esprit.autoloc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final IAgenceService service;

    public AgenceController(IAgenceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Agence> ajouter(
            @RequestBody Agence entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.ajouter(entity));
    }

    @GetMapping
    public List<Agence> afficherTous() {
        return service.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> chercherParId(
            @PathVariable Long id) {
        return service.chercherParId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public Agence modifier(@RequestBody Agence entity) {
        return service.modifier(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
