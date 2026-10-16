
package tn.esprit.autoloc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final IReservationService service;

    public ReservationController(IReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Reservation> ajouter(
            @RequestBody Reservation e) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.ajouter(e));
    }

    @GetMapping
    public List<Reservation> afficherTous() {
        return service.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> chercherParId(
            @PathVariable Long id) {
        return service.chercherParId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public Reservation modifier(@RequestBody Reservation e) {
        return service.modifier(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
