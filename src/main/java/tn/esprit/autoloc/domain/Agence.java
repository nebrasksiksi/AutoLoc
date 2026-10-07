package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import lombok.Data;
@Entity
@Table(name = "agence")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agence {
    @OneToMany(mappedBy = "agence")
    private List<Employe> employes;

    @OneToMany(mappedBy = "agence")
    private List<Vehicule> vehicules;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String ville;

    @Column(length = 255)
    private String adresse;

    @Column(length = 20)
    private String telephone;
}