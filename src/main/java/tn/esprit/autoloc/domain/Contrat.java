package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
@Entity
@Table(name = "contrat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @OneToOne
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @OneToOne(mappedBy = "contrat")
    private Paiement paiement;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false)
    private LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(nullable = false)
    private boolean valide;
}