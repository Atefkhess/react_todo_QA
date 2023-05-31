package com.e2eTest.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.NoSuchElementException;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

/**
 * This class is used to perform various kinds of validations in the test cases.
 */
public class Validations extends BasePage {

	/** testCaseStatus the status of the test case. */
	boolean testCaseStatus = true;

	/** test status. */
	boolean testStatus;

	/** test screenshot dir. */
	private String testScreenshotDir;

	private WebDriver driver;

	/**
	 * Instanciation de assertions.
	 */
	public Validations() {
		super();
		this.driver = Setup.getDriver();
	}

	/**
	 * method verify whether element is present on screen.
	 *
	 * @param targetElement element to be present
	 * @return true if element is present else throws exception
	 */
	public Boolean isElementPresent(By targetElement) {
		return Setup.getDriver().findElements(targetElement).size() > 0;
	}

	/**
	 * methode Checks if is element displayed.
	 *
	 * @param element element web
	 * @return boolean
	 */
	public Boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	/**
	 * methode Checks if is element selected
	 *
	 * @param element
	 * @return boolean
	 */
	public Boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}

	/**
	 * methode Checks if is element enabled.
	 *
	 * @param element
	 * @return boolean
	 */
	public Boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	/**
	 * method verify whether element is not present on screen.
	 *
	 * @param targetElement element not to be present
	 * @return true if element is not present else throws exception
	 */
	public Boolean isElementNotPresent(By targetElement) {
		return Setup.getDriver().findElements(targetElement).size() == 0;
	}

	/**
	 * method to take screenshot.
	 *
	 * @return path where screenshot has been saved
	 */
	public String screenShot() {
		String screenshotPath = "screenshot"
				+ new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss", Locale.FRANCE).format(new GregorianCalendar().getTime())
				+ ".png";

		log.info(screenshotPath);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(testScreenshotDir + screenshotPath));
		} catch (IOException e) {
			log.info("Exception: ", e);
			screenshotPath = "";
		}
		return screenshotPath;
	}

	/**
	 * methode Verif PDF text file.
	 *
	 * @param text
	 * @param pdfPath
	 * @return true, si c'est vrai
	 * @throws IOException Signal qu'une execption de type I/O s'est produite.
	 */
	public boolean verifyTextInFile(String text, String pdfPath) throws IOException {

		boolean exist = false;
		File file = new File(pdfPath);
		FileInputStream fis = new FileInputStream(file);
		PDFParser parser = new PDFParser(fis);

		parser.parse();

		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);
		PDFTextStripper strip = new PDFTextStripper();
		String data = strip.getText(pdDoc);

		exist = data.contains(text);

		cosDoc.close();
		pdDoc.close();

		log.info("Text Found on the pdf File...");
		return exist;

	}

	public void assertEquals(WebElement element, String text) {
		String text1 = element.getText();
		Assert.assertEquals(text1, text);

	}

	public void assertTrue(WebElement element, String text) {
		String text1 = element.getText();
		Assert.assertTrue(text1.contains(text));

	}

	/**
	 * methode Column contains value.
	 *
	 * @param pathToFile
	 * @param columnIndex
	 * @param targetValue
	 * @return true, si c'est vrai
	 * @throws IOException Signal qu'une execption de type I/O s'est produite.
	 */
	public boolean columnContainsValue(String pathToFile, int columnIndex, String targetValue) throws IOException {

		Reader in = new FileReader(pathToFile);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

		for (CSVRecord record : records) {
			String rowValue = record.get(columnIndex);
			if (targetValue.equals(rowValue))
				return true;
		}
		return false;
	}

	/**
	 * methode Check field is empty.
	 * 
	 * @param elementAttr
	 */
	public void checkFieldIsEmpty(WebElement elementAttr) {
		WebElement inputText = elementAttr;
		String text = inputText.getAttribute("value");

		if (text.isEmpty()) {
			log.info("input box is empty");
		}
	}

	// Retrieve the performance logs and check if any network requests were made
	public void checkNetworkRequest() {
		List<LogEntry> logEntries = driver.manage().logs().get(LogType.BROWSER).getAll();
		boolean requestMade = false;
		for (LogEntry logEntry : logEntries) {
			String message = logEntry.getMessage();
			if (message.contains("Network.requestWillBeSent")) {
				requestMade = true;
				break;
			}
		}
		Assert.assertFalse("Network request was made when form button was disabled", requestMade);
	}

	public void assertFalseCondition(boolean condition) {
		Assert.assertFalse(condition);

	}

	public void assertTrueCondition(Boolean condition) {
		Assert.assertTrue(condition);
	}
	public void checkElementDisabled(WebElement element) {
	    if (element.isEnabled()) {
	        System.out.println("The element is enabled.");
	    } else {
	        System.out.println("The element is disabled.");
	    }
	}
	public void checkElementPresent(WebElement element) {
	    try {
	        if (element.isDisplayed()) {
	            System.out.println("The element is displayed.");
	        } else {
	            System.out.println("The element is  not displayed.");
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("The element is not found.");
	    }
	}
//	public boolean isElementPresent(WebElement element) {
//	    try {
//	        return element.isDisplayed();
//	    } catch (NoSuchElementException e) {
//	        // Handle the exception based on your requirements
//	        // For example, you can throw a custom exception or log an error message
//	        System.out.println("Element not found: " + e.getMessage());
//	        return false;
//	    }
//	}

	}
