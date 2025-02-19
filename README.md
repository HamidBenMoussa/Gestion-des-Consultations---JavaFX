# Gestion des Consultations - JavaFX

Une application JavaFX permettant de gérer les consultations et les patients de manière simple et efficace.

## Objectif
Développer une interface graphique pour **ajouter, modifier, supprimer et afficher** les patients et leurs consultations. Cette application vise à simplifier la gestion des dossiers médicaux en offrant une interface intuitive et des fonctionnalités complètes.

## Fonctionnalités

### Modèles de données
- Création des classes **`Consultation`** et **`Patient`** avec :
  - Des **attributs** pour stocker les informations essentielles comme le nom, le prénom, le téléphone pour les patients, et la date, le motif pour les consultations.
  - Des **constructeurs** pour initialiser les objets avec les données nécessaires.
  - Des **getters/setters** pour accéder et modifier les attributs des objets.
  - Une méthode **`toString()`** pour un affichage lisible des informations des objets.

### Interface utilisateur
- Vue principale **`main-view.fxml`** intégrant un `TabPane` pour naviguer entre les sections de gestion des patients et des consultations.
- Deux vues spécifiques :
  - **`patients-view.fxml`** : Contient un formulaire pour ajouter/modifier les patients et une liste pour afficher tous les patients.
  - **`consultations-view.fxml`** : Contient un formulaire pour ajouter/modifier les consultations et une liste pour afficher toutes les consultations.

### Contrôleurs
- **`PatientController`** et **`ConsultationController`** pour gérer les **événements utilisateur** et l’interaction avec les données. Ces contrôleurs sont responsables de la logique de l'application, comme l'ajout, la modification, la suppression et la recherche des patients et des consultations.

### Design et ergonomie
- Intégration du **style BootstrapFX** pour une interface moderne et intuitive. BootstrapFX est une bibliothèque de styles qui permet de donner un aspect professionnel et cohérent à l'application.

### Exécution de l’application
- Création d’une **classe principale** avec la méthode **`start()`** pour initialiser et lancer l’application. Cette classe configure la scène principale et charge les vues FXML.

