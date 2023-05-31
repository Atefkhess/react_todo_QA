package com.e2eTest.automation.page_objects.connexion;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.e2eTest.automation.utils.Setup;

/* Retrieve Element */
/* Cachelookup will ask to Selenium to save the cache of this Webelement
 *  instead of looking for it in the webpage */
//@CacheLookup 

//public static WebElement email;

public class ConnexionPage {

	//
//	public LoginPage() {
//        super(Setup.getDriver());
//    }

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//input[@type='email']")
	public static WebElement email;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	public static WebElement password;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	public static WebElement envoyerBtn;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//h2[normalize-space()='Créer une nouvelle tâche']")
	public static WebElement ajoutTachePage;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger']")
	public static WebElement alertMsg;
	

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Tâches']")
	public static WebElement tacheIcon;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[1]")
	public static WebElement tacheInput;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[2]")
	public static WebElement tacheDescr;
	
	@FindBy(how = How.XPATH, using ="//button[@class='btn btn-primary']")
	public static WebElement ajoutTacheBtn;//
	
	@FindBy(how = How.XPATH, using ="//div[@class='container mt-5']//span[1]")
	public static WebElement localStorage;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Déconnexion']")
	public static WebElement deconnexionIcon;
	
	

	/*
	 * Initialize the elements(of a given page) with Pagefactory(design pattern)
	 */
	public ConnexionPage() {
		PageFactory.initElements(Setup.getDriver(), this);
	}

	

}
