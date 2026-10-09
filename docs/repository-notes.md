
# Atelier 3 — Spring Data JPA Repository

## 1. Choix de l'interface

Les neuf repositories utilisent JpaRepository<Entite, Long>.

Justification :
- CRUD complet.
- findAll() renvoie une List.
- Tri et pagination disponibles.
- Méthodes spécifiques à JPA, notamment saveAndFlush().

## 2. Repositories

| Interface | Entité | Justification |
|---|---|---|
| IAgenceRepository | Agence | CRUD complet |
| IEmployeRepository | Employe | CRUD complet |
| IVehiculeRepository | Vehicule | CRUD complet |
| IEquipementRepository | Equipement | CRUD complet |
| IClientRepository | Client | CRUD complet |
| IReservationRepository | Reservation | CRUD complet |
| IContratRepository | Contrat | CRUD complet |
| IPaiementRepository | Paiement | Lecture et accès aux paiements |
| IMaintenanceRepository | Maintenance | CRUD complet |

## 3. Anomalies SonarQube for IDE

### Anomalie 1
Problème réellement détecté :
Règle :
Correction apportée :

### Anomalie 2
Problème réellement détecté :
Règle :
Correction apportée :

### Anomalie 3
Problème réellement détecté :
Règle :
Correction apportée :

## 4. Vérification

- Les neuf repositories sont créés.
- Chaque repository étend JpaRepository avec une clé Long.
- L'application démarre correctement.
- Les logs indiquent la détection des neuf repositories.
- La configuration Hibernate conserve ddl-auto=update.
