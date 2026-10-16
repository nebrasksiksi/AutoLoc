
package tn.esprit.autoloc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {
    private final IMaintenanceService service;

    public MaintenanceController(IMaintenanceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Maintenance> ajouter(
            @RequestBody Maintenance e) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.ajouter(e));
    }

    @GetMapping
    public List<Maintenance> afficherTous() {
        return service.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> chercherParId(
            @PathVariable Long id) {
        return service.chercherParId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public Maintenance modifier(@RequestBody Maintenance e) {
        return service.modifier(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
