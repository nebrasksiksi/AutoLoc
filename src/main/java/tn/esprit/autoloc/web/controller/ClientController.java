
package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Lister tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste récupérée")
    @GetMapping
    public List<Client> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Récupérer un client par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Créer un client")
    @ApiResponse(responseCode = "201", description = "Client créé")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return service.create(client);
    }

    @Operation(summary = "Modifier un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifié"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @PutMapping("/{id}")
    public Client update(
            @PathVariable Long id,
            @RequestBody Client client) {
        return service.update(id, client);
    }

    @Operation(summary = "Supprimer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
