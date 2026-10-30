package tn.esprit.autoloc.web.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.dto.PaiementDTO;
import tn.esprit.autoloc.mapper.PaiementMapper;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {
    private final IPaiementService service;
    private final PaiementMapper mapper;

    public PaiementController(IPaiementService service, PaiementMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaiementDTO ajouter(@Valid @RequestBody PaiementDTO dto) {
        return mapper.toDTO(service.ajouter(mapper.toEntity(dto)));
    }

    @GetMapping
    public List<PaiementDTO> afficherTous() {
        return service.afficherTous().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public PaiementDTO chercherParId(@PathVariable Long id) {
        Paiement paiement = service.chercherParId(id)
                .orElseThrow(() -> new EntityNotFoundException("Paiement introuvable : " + id));
        return mapper.toDTO(paiement);
    }

    @PutMapping
    public PaiementDTO modifier(@Valid @RequestBody PaiementDTO dto) {
        return mapper.toDTO(service.modifier(mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}
