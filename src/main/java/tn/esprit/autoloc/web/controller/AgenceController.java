
package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Lister toutes les agences")
    @ApiResponse(responseCode = "200", description = "Liste récupérée")
    @GetMapping
    public List<Agence> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Récupérer une agence par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @GetMapping("/{id}")
    public Agence getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Créer une agence")
    @ApiResponse(responseCode = "201", description = "Agence créée")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agence create(@RequestBody Agence agence) {
        return service.create(agence);
    }

    @Operation(summary = "Modifier une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence modifiée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @PutMapping("/{id}")
    public Agence update(
            @PathVariable Long id,
            @RequestBody Agence agence) {
        return service.update(id, agence);
    }

    @Operation(summary = "Supprimer une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
