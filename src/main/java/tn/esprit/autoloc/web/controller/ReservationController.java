package tn.esprit.autoloc.web.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.dto.ReservationDTO;
import tn.esprit.autoloc.mapper.ReservationMapper;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final IReservationService service;
    private final ReservationMapper mapper;

    public ReservationController(IReservationService service, ReservationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDTO ajouter(@Valid @RequestBody ReservationDTO dto) {
        return mapper.toDTO(service.ajouter(mapper.toEntity(dto)));
    }

    @GetMapping
    public List<ReservationDTO> afficherTous() {
        return service.afficherTous().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ReservationDTO chercherParId(@PathVariable Long id) {
        Reservation reservation = service.chercherParId(id)
                .orElseThrow(() -> new EntityNotFoundException("Réservation introuvable : " + id));
        return mapper.toDTO(reservation);
    }

    @PutMapping
    public ReservationDTO modifier(@Valid @RequestBody ReservationDTO dto) {
        return mapper.toDTO(service.modifier(mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}
