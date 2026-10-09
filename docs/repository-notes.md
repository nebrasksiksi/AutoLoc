# Atelier 3 — Spring Data JPA Repository

## 1. Choix de l'interface

Les neuf repositories utilisent `JpaRepository<Entite, Long>`.

**Justification :**

- CRUD complet : création, lecture, modification et suppression des entités.
- La méthode `findAll()` renvoie une `List`.
- Le tri et la pagination sont disponibles.
- Des méthodes spécifiques à JPA sont disponibles, notamment `saveAndFlush()`.

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
| IPaiementRepository | Paiement | CRUD complet |
| IMaintenanceRepository | Maintenance | CRUD complet |

## 3. Anomalies SonarQube for IDE

### Anomalie 1 — Import inutilisé de Getter

**Problème détecté :** l'import `lombok.Getter` est déclaré mais n'est pas utilisé dans le fichier.

**Règle :** `java:S1128` — Remove this unused import.

**Fichier concerné :** `Agence.java` (également signalé dans plusieurs autres entités).

**Correction apportée :** supprimer la ligne `import lombok.Getter;` si aucune annotation `@Getter` n'est utilisée dans le fichier.

### Anomalie 2 — Import inutilisé de Setter

**Problème détecté :** l'import `lombok.Setter` est déclaré mais n'est pas utilisé dans le fichier.

**Règle :** `java:S1128` — Remove this unused import.

**Fichier concerné :** `Agence.java` (également signalé dans plusieurs autres entités).

**Correction apportée :** supprimer la ligne `import lombok.Setter;` si aucune annotation `@Setter` n'est utilisée dans le fichier.

### Anomalie 3 — Imports inutilisés dans les entités

**Problème détecté :** plusieurs entités contiennent des imports Lombok inutilisés, notamment `Getter` et `Setter`.

**Règle :** `java:S1128` — Remove this unused import.

**Fichiers concernés :** `Client.java`, `Contrat.java`, `Employe.java` et `Equipement.java`, entre autres fichiers visibles dans le rapport.

**Correction apportée :** examiner les imports de chaque fichier et supprimer uniquement ceux qui ne sont pas utilisés. Conserver les annotations Lombok réellement nécessaires au fonctionnement de l'entité.

## 4. Vérification

- Les neuf interfaces Repository sont créées.
- Chaque repository étend `JpaRepository` avec le type d'entité correspondant et une clé primaire de type `Long`.
- L'application démarre correctement après vérification.
- Les logs confirment la détection des neuf repositories.
- La configuration Hibernate conserve `spring.jpa.hibernate.ddl-auto=update`.
- Les imports inutilisés détectés par SonarQube for IDE sont supprimés.
- Une nouvelle analyse SonarQube for IDE est effectuée après les corrections.