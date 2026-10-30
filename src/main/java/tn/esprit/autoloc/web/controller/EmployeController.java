package tn.esprit.autoloc.web.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.dto.EmployeDTO;
import tn.esprit.autoloc.mapper.EmployeMapper;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {
    private final IEmployeService service;
    private final EmployeMapper mapper;

    public EmployeController(IEmployeService service, EmployeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeDTO ajouter(@Valid @RequestBody EmployeDTO dto) {
        return mapper.toDTO(service.ajouter(mapper.toEntity(dto)));
    }

    @GetMapping
    public List<EmployeDTO> afficherTous() {
        return service.afficherTous().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public EmployeDTO chercherParId(@PathVariable Long id) {
        Employe employe = service.chercherParId(id)
                .orElseThrow(() -> new EntityNotFoundException("Employé introuvable : " + id));
        return mapper.toDTO(employe);
    }

    @PutMapping
    public EmployeDTO modifier(@Valid @RequestBody EmployeDTO dto) {
        return mapper.toDTO(service.modifier(mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}
