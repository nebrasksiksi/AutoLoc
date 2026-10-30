package tn.esprit.autoloc.web.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.dto.EquipementDTO;
import tn.esprit.autoloc.mapper.EquipementMapper;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {
    private final IEquipementService service;
    private final EquipementMapper mapper;

    public EquipementController(IEquipementService service, EquipementMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDTO ajouter(@Valid @RequestBody EquipementDTO dto) {
        return mapper.toDTO(service.ajouter(mapper.toEntity(dto)));
    }

    @GetMapping
    public List<EquipementDTO> afficherTous() {
        return service.afficherTous().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public EquipementDTO chercherParId(@PathVariable Long id) {
        Equipement equipement = service.chercherParId(id)
                .orElseThrow(() -> new EntityNotFoundException("Équipement introuvable : " + id));
        return mapper.toDTO(equipement);
    }

    @PutMapping
    public EquipementDTO modifier(@Valid @RequestBody EquipementDTO dto) {
        return mapper.toDTO(service.modifier(mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}
