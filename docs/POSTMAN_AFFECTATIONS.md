# Tests Postman — 5 services d'affectation AutoLoc

Base URL : `http://localhost:8081`

> Les identifiants `1` sont des exemples. Ils doivent exister dans la base `autoloc_db`.
> Les contrôleurs de ce projet ont le préfixe `/api`.

## 1. Affecter une réservation à un client
- Méthode : `PUT`
- URL : `http://localhost:8081/api/reservations/1/client?nom=Ben%20Ali&prenom=Ahmed`
- Body : aucun (`none`)
- Réponse attendue : `ReservationResponseDTO`.

Le client doit déjà exister avec exactement ce nom et ce prénom, et la réservation `1` doit exister.

## 2. Affecter un équipement à un véhicule
- Méthode : `PUT`
- URL : `http://localhost:8081/api/vehicules/1/equipements/1`
- Body : aucun (`none`)
- Réponse attendue : `VehiculeDTO`.

Le véhicule `1` et l'équipement `1` doivent exister.

## 3. Affecter une maintenance à un véhicule
- Méthode : `POST`
- URL : `http://localhost:8081/api/vehicules/maintenance?description=Vidange%20et%20changement%20du%20filtre&immatriculation=123%20TU%20456`
- Body : aucun (`none`)
- Réponse attendue : `VehiculeDTO`.

Remplacer l'immatriculation par celle d'un véhicule déjà enregistré.

## 4. Affecter un contrat à une réservation
- Méthode : `POST`
- URL : `http://localhost:8081/api/contrats/affecter-reservation?dateSignature=2026-11-22&dateDebut=2026-11-23&dateFin=2026-11-30`
- Body : aucun (`none`)
- Réponse attendue : `ContratDTO`.

La signature demandée par le sujet ne contient pas d'identifiant de réservation. L'implémentation retrouve donc la réservation avec la paire exacte `dateDebut` + `dateFin`. Cette paire doit correspondre à une seule réservation existante.

## 5. Créer une réservation et son contrat associé
- Méthode : `POST`
- URL : `http://localhost:8081/api/reservations/avec-contrat`
- Header : `Content-Type: application/json`
- Body → raw → JSON :

```json
{
  "vehiculeId": 1,
  "clientId": 1,
  "dateDebut": "2026-11-23",
  "dateFin": "2026-11-30",
  "statut": "EN_ATTENTE",
  "dateSignature": "2026-11-22",
  "valide": true
}
```

`montantTotal` est facultatif : s'il est absent, le service le calcule à partir du tarif journalier du véhicule et du nombre de jours. `vehiculeId` et `clientId` doivent désigner des enregistrements existants.

## Codes de réponse
- `200 OK` pour les affectations sans création de ressource.
- `201 Created` pour la création réservation + contrat.
- `404 Not Found` si un ID, un client, un véhicule ou une réservation n'existe pas.
- `400 Bad Request` si les paramètres ou les dates sont invalides.

Démarrer MySQL puis l'application Spring Boot avant les tests. La configuration locale est dans `src/main/resources/application.properties`.
