package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.dto.AgenceDTO;

@Component
public class AgenceMapper {
    public AgenceDTO toDTO(Agence agence) {
        return agence == null ? null : new AgenceDTO(
                agence.getIdAgence(), agence.getNom(), agence.getVille(),
                agence.getAdresse(), agence.getTelephone());
    }

    public Agence toEntity(AgenceDTO dto) {
        if (dto == null) return null;
        Agence agence = new Agence();
        agence.setIdAgence(dto.idAgence());
        agence.setNom(dto.nom());
        agence.setVille(dto.ville());
        agence.setAdresse(dto.adresse());
        agence.setTelephone(dto.telephone());
        return agence;
    }
}
