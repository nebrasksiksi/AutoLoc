package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeDTO;

@Component
public class VehiculeMapper {
    public VehiculeDTO toDTO(Vehicule vehicule) {
        return vehicule == null ? null : new VehiculeDTO(
                vehicule.getIdVehicule(),
                vehicule.getAgence() == null ? null : vehicule.getAgence().getIdAgence(),
                vehicule.getImmatriculation(), vehicule.getMarque(), vehicule.getModele(),
                vehicule.getCategorie(), vehicule.getTarifJournalier(), vehicule.getStatut());
    }

    public Vehicule toEntity(VehiculeDTO dto) {
        if (dto == null) return null;
        Vehicule vehicule = new Vehicule();
        vehicule.setIdVehicule(dto.idVehicule());
        vehicule.setImmatriculation(dto.immatriculation());
        vehicule.setMarque(dto.marque());
        vehicule.setModele(dto.modele());
        vehicule.setCategorie(dto.categorie());
        vehicule.setTarifJournalier(dto.tarifJournalier());
        vehicule.setStatut(dto.statut());
        if (dto.agenceId() != null) {
            Agence agence = new Agence();
            agence.setIdAgence(dto.agenceId());
            vehicule.setAgence(agence);
        }
        return vehicule;
    }
}
