package com.e2eTest.automation.step_definitions;

import com.e2eTest.automation.page_objects.connexion.ConnexionPage;
import com.e2eTest.automation.utils.ConfigFileReader;
import com.e2eTest.automation.utils.SeleniumUtils;
import com.e2eTest.automation.utils.Validations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AuthentificationStepDef1 extends SeleniumUtils  {

	Validations validations = new Validations();
	
	
	public ConnexionPage connexion ;


	private ConfigFileReader configFileReader;
	public AuthentificationStepDef1() {
		
		connexion = new ConnexionPage();
	
		this.configFileReader = new ConfigFileReader();
	}
	
	
	@When("Je saisis le email")
	public void je_saisis_le_email() {
	    super.writeText(ConnexionPage.email,configFileReader.getProperties("email"));
	}

	@When("Je saisis le password")
	public void je_saisis_le_password() {
		super.writeText(ConnexionPage.password,configFileReader.getProperties("password"));
	}

	@When("Je clique sur le bouton Envoyer")
	public void je_clique_sur_le_bouton_envoyer() {
		super.click(ConnexionPage.envoyerBtn);
	}

	@Then("Je me redirige vers la page Ajout Taches")
	public void je_me_redirige_vers_la_page_ajout_taches() {
	    validations.isElementDisplayed(ConnexionPage.ajoutTachePage);
	}


}
