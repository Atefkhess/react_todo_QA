@validation
Feature: Validation de formulaire, mise à jour du stockage local, validation de connexion et liens de navigation
  Je veux faire une serie de tests sur les fonctionnalités de l application TODO List

  Scenario: Validation de formulaire, mise à jour du stockage local, validation de connexion et liens de navigation
    Given utilisateur est sur la page de connexion
    Then les liens Tâches et Déconnexion apparaissent seulement quand l utilisateur est identifie
    And Lorsque l utilisateur saisit des identifiants de connexion incorrects "test123" et "test" et soumet un message d alerte s affiche
    When tous les champs de formulaire "test@test.com" et "test" ne sont pas remplis les boutons connexion et ajout de tâches ne sont pas activés
    And aucun appel de requête n est envoyé
    When les champs "test@test.com" et "test" sont remplis
    And je clique sur le bouton connexion
    And j ajoute une tache "test fonctionnel" "commencer par le test fonctionnel unitaire"
    Then les donnees dans localstorage sont maj "commencer par le test fonctionnel unitaire"