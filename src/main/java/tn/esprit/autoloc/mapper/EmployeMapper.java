package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.dto.EmployeDTO;

@Component
public class EmployeMapper {
    public EmployeDTO toDTO(Employe employe) {
        return employe == null ? null : new EmployeDTO(
                employe.getIdEmploye(),
                employe.getAgence() == null ? null : employe.getAgence().getIdAgence(),
                employe.getNom(), employe.getPrenom(), employe.getRole());
    }

    public Employe toEntity(EmployeDTO dto) {
        if (dto == null) return null;
        Employe employe = new Employe();
        employe.setIdEmploye(dto.idEmploye());
        employe.setNom(dto.nom());
        employe.setPrenom(dto.prenom());
        employe.setRole(dto.role());
        if (dto.agenceId() != null) {
            Agence agence = new Agence();
            agence.setIdAgence(dto.agenceId());
            employe.setAgence(agence);
        }
        return employe;
    }
}
