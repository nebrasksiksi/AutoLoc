
package tn.esprit.autoloc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final IClientService service;

    public ClientController(IClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Client> ajouter(@RequestBody Client e) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.ajouter(e));
    }

    @GetMapping
    public List<Client> afficherTous() {
        return service.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> chercherParId(
            @PathVariable Long id) {
        return service.chercherParId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public Client modifier(@RequestBody Client e) {
        return service.modifier(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
