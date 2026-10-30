package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.dto.ClientDTO;

@Component
public class ClientMapper {
    public ClientDTO toDTO(Client client) {
        return client == null ? null : new ClientDTO(
                client.getIdClient(), client.getNom(), client.getPrenom(),
                client.getEmail(), client.getTelephone(), client.getNumPermis(),
                client.getDateInscription());
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;
        Client client = new Client();
        client.setIdClient(dto.idClient());
        client.setNom(dto.nom());
        client.setPrenom(dto.prenom());
        client.setEmail(dto.email());
        client.setTelephone(dto.telephone());
        client.setNumPermis(dto.numPermis());
        client.setDateInscription(dto.dateInscription());
        return client;
    }
}
