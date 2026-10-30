package tn.esprit.autoloc.web.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.mapper.ContratMapper;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {
    private final IContratService service;
    private final ContratMapper mapper;

    public ContratController(IContratService service, ContratMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDTO ajouter(@Valid @RequestBody ContratDTO dto) {
        return mapper.toDTO(service.ajouter(mapper.toEntity(dto)));
    }

    @GetMapping
    public List<ContratDTO> afficherTous() {
        return service.afficherTous().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ContratDTO chercherParId(@PathVariable Long id) {
        Contrat contrat = service.chercherParId(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable : " + id));
        return mapper.toDTO(contrat);
    }

    @PutMapping
    public ContratDTO modifier(@Valid @RequestBody ContratDTO dto) {
        return mapper.toDTO(service.modifier(mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}
