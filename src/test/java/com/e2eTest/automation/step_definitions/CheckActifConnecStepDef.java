package com.e2eTest.automation.step_definitions;

import com.e2eTest.automation.page_objects.connexion.ConnexionPage;
import com.e2eTest.automation.utils.ConfigFileReader;
import com.e2eTest.automation.utils.SeleniumUtils;
import com.e2eTest.automation.utils.Validations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckActifConnecStepDef {

	Validations validations = new Validations();
	SeleniumUtils utils = new SeleniumUtils();
	public ConnexionPage connexion;

	private ConfigFileReader configFileReader;

	public CheckActifConnecStepDef() {

		connexion = new ConnexionPage();

		this.configFileReader = new ConfigFileReader();
	}

	@Given("utilisateur est sur la page de connexion")
	public void utilisateurEstSurLaPageDeConnexion() {
		utils.get(configFileReader.getProperties("home.url"));
	}

	@Then("les liens Tâches et Déconnexion apparaissent seulement quand l utilisateur est identifie")
	public void lesLiensTâchesEtDéconnexionApparaissentSeulementQuandLUtilisateurEstIdentifie() {
		validations.isElementEnabled(ConnexionPage.tacheIcon);
		validations.isElementEnabled(ConnexionPage.deconnexionIcon);
	}

	@Then("Lorsque l utilisateur saisit des identifiants de connexion incorrects {string} et {string} et soumet un message d alerte s affiche")
	public void lorsqueLUtilisateurSaisitDesIdentifiantsDeConnexionIncorrectsEtEtSoumetUnMessageDAlerteSAffiche(
			String email, String password) {
		utils.writeText(ConnexionPage.email, email);
		utils.writeText(ConnexionPage.password, password);
		utils.click(ConnexionPage.envoyerBtn);
		validations.assertEquals(ConnexionPage.alertMsg, ConnexionPage.alertMsg.getText());
	}

	@When("tous les champs de formulaire {string} et {string} ne sont pas remplis les boutons connexion et ajout de tâches ne sont pas activés")
	public void tousLesChampsDeFormulaireEtNeSontPasRemplisLesBoutonsConnexionEtAjoutDeTâchesNeSontPasActivés(
			String email, String password) {

		utils.clearField(ConnexionPage.email);
		utils.clearField(ConnexionPage.password);

		validations.assertTrueCondition(validations.isElementEnabled(ConnexionPage.envoyerBtn));
		//utils.click(ConnexionPage.envoyerBtn);

		validations.checkElementDisabled(ConnexionPage.envoyerBtn);
		validations.checkElementPresent(ConnexionPage.ajoutTacheBtn);
	}

	@Then("aucun appel de requête n est envoyé")
	public void aucunAppelDeRequêteNEstEnvoyé() {
		validations.checkNetworkRequest();

	}

	@When("les champs {string} et {string} sont remplis")
	public void lesChampsEtSontRemplis(String email, String password) {
		utils.writeText(ConnexionPage.email, email);
		utils.writeText(ConnexionPage.password, password);
	}

	@When("je clique sur le bouton connexion")
	public void jeCliqueSurLeBoutonConnexion() {
		utils.click(ConnexionPage.envoyerBtn);

	}

	@When("j ajoute une tache {string} {string}")
	public void jAjouteUneTache(String tache, String descr) {
		utils.writeText(ConnexionPage.tacheInput, tache);
		utils.writeText(ConnexionPage.tacheDescr, descr);
		utils.click(ConnexionPage.ajoutTacheBtn);

	}

	@Then("les donnees dans localstorage sont maj {string}")
	public void lesDonneesDansLocalstorageSontMaj(String descr) {
		validations.assertTrue(ConnexionPage.localStorage, descr);

	}

}
