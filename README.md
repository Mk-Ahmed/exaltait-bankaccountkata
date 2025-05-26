_#

### Niveau d’engagement

Ce kata a été réalisé en respectant le niveau d’engagement 1.

### Choix de modélisation des comptes bancaires

Pour représenter les types de comptes (compte courant et livret A), j’ai opté pour la création d’une interface Account,
implémentée par deux classes : CurrentAccount et SavingAccount.

Ce choix permet de :

- Clarifier les règles métiers propres à chaque type de compte,
- Préparer facilement l’ajout de nouveaux types de comptes à l’avenir,
- Séparer les responsabilités métier

Bien que cela implique une légère duplication de code, j'évite une hiérarchie abstraite prématurée.

### Sécurité des opérations concurrentes

Les opérations comme credit et debit sont sensibles aux conditions de concurrence.
Dans un environnement de production, il serait indispensable d’appliquer un verrouillage pessimiste (ex.SELECT FOR
UPDATE) pour éviter les accès concurrents aux lignes de la base de données.

### Relevé de compte (Feature 4)

Pour la génération d’un relevé bancaire, chaque opération (crédit ou débit) devrait générer un Domain Event.
Ces événements peuvent être écoutés par des listeners qui enregistrent les opérations dans une structure optimisée pour
la lecture.

Cela permettrait de :

- Conserver l’historique complet des actions,
- Générer des relevés à la demande
- Rendre possible une logique d’audit ou d’export (PDF, CSV…).


