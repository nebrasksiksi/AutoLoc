package tn.esprit.autoloc.web.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.MaintenanceDTO;
import tn.esprit.autoloc.mapper.MaintenanceMapper;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {
    private final IMaintenanceService service;
    private final MaintenanceMapper mapper;

    public MaintenanceController(IMaintenanceService service, MaintenanceMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO ajouter(@Valid @RequestBody MaintenanceDTO dto) {
        return mapper.toDTO(service.ajouter(mapper.toEntity(dto)));
    }

    @GetMapping
    public List<MaintenanceDTO> afficherTous() {
        return service.afficherTous().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public MaintenanceDTO chercherParId(@PathVariable Long id) {
        Maintenance maintenance = service.chercherParId(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance introuvable : " + id));
        return mapper.toDTO(maintenance);
    }

    @PutMapping
    public MaintenanceDTO modifier(@Valid @RequestBody MaintenanceDTO dto) {
        return mapper.toDTO(service.modifier(mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}
