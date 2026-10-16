
package tn.esprit.autoloc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {
    private final IVehiculeService service;

    public VehiculeController(IVehiculeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Vehicule> ajouter(@RequestBody Vehicule e) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.ajouter(e));
    }

    @GetMapping
    public List<Vehicule> afficherTous() {
        return service.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> chercherParId(
            @PathVariable Long id) {
        return service.chercherParId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public Vehicule modifier(@RequestBody Vehicule e) {
        return service.modifier(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
