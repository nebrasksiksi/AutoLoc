package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import lombok.Data;
@Entity
@Table(name = "equipement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {

    @ManyToMany(mappedBy = "equipements")
    private List<Vehicule> vehicules;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    @Column(nullable = false, length = 100)
    private String libelle;
}