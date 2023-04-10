package com.e2eTest.automation.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Setup {
	
	

	private static Process webServerProcess;	  

	private static WebDriver driver;

	/**
	 * This method is used to open browser. This method is called before the
	 * invocation of each test method in the given class. In this method we need to
	 * pass browser name which will invoke the respective driver.
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 * @throws MalformedURLException the malformed URL exception
	 * @Before Methods annotated with @Before will execute before every scenario.
	 */
	static boolean isWindows() {
	    return System.getProperty("os.name").toLowerCase().contains("win");
	}
	static String npm = isWindows() ? "npm.cmd" : "npm";

	@Before // Hooks Of Cucumber
	public void setWebDriver() throws InterruptedException, IOException {
		
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\nodejs\\npm.cmd", "start");
		processBuilder.directory(new File("C:\\Users\\Emichuu\\Desktop\\Formations the wa center\\Formation automatisation\\react-todolist-qa"));
		processBuilder.redirectOutput();
		webServerProcess = processBuilder.start();
	   
		// Wait for the React project to start
			

	    System.out.println("Website opened in Chrome");
//        // Attendre que le serveur soit prêt avant de lancer les tests
//        String output = "";
//        while (!output.contains("Server started")) {
//            Thread.sleep(1000);
//            output = new String(webServerProcess.getInputStream().readAllBytes());
//            
//        }
//        
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		switch (browser) {
		case "chrome":
			/*System.setProperty("webdriver.chrome.driver", "src/test/resource/drivers/win/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new ChromeDriver();
			*/
			 System.setProperty("webdriver.http.factory", "jdk-http-client");
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			// Both do the same thing
			driver.manage().window().maximize();
			chromeOptions.addArguments("['start-maxized']");
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/test/resource/drivers/win/geckodriver.exe");
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("platform", Platform.WIN10);
			firefoxOptions.setProfile(profile);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		default:
			throw new IllegalArgumentException("Browser\"" + browser + "\" is not supported. ");
		}
		//PageFactory.initElements(Setup.driver,LoginPage.class);
		//System.out.println("this:"+this);

	}

	/* Getter */
	public static WebDriver getDriver() {
		return driver;
	}

    public static Process getWebServerProcess() {
		return webServerProcess;
	}


}