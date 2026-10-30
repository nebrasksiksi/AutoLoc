package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.dto.EquipementDTO;

@Component
public class EquipementMapper {
    public EquipementDTO toDTO(Equipement equipement) {
        return equipement == null ? null : new EquipementDTO(
                equipement.getIdEquipement(), equipement.getLibelle());
    }

    public Equipement toEntity(EquipementDTO dto) {
        if (dto == null) return null;
        Equipement equipement = new Equipement();
        equipement.setIdEquipement(dto.idEquipement());
        equipement.setLibelle(dto.libelle());
        return equipement;
    }
}
