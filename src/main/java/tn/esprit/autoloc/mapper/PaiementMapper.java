package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.dto.PaiementDTO;

@Component
public class PaiementMapper {
    public PaiementDTO toDTO(Paiement paiement) {
        return paiement == null ? null : new PaiementDTO(
                paiement.getIdPaiement(),
                paiement.getContrat() == null ? null : paiement.getContrat().getIdContrat(),
                paiement.getMontant(), paiement.getDatePaiement(), paiement.getModePaiement());
    }

    public Paiement toEntity(PaiementDTO dto) {
        if (dto == null) return null;
        Paiement paiement = new Paiement();
        paiement.setIdPaiement(dto.idPaiement());
        paiement.setMontant(dto.montant());
        paiement.setDatePaiement(dto.datePaiement());
        paiement.setModePaiement(dto.modePaiement());
        if (dto.contratId() != null) {
            Contrat contrat = new Contrat();
            contrat.setIdContrat(dto.contratId());
            paiement.setContrat(contrat);
        }
        return paiement;
    }
}
