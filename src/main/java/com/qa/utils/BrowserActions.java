package com.qa.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.qa.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class BrowserActions extends TestBase {

	private static final Logger logger = Logger.getLogger(BrowserActions.class);
	public static boolean VerifyPageTitle(String expectedTitle) throws Exception {
		boolean flag = false;
		String actualTitle = driver.getTitle();
		System.out.println("Page Title :- " + actualTitle);

		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			flag = true;
		}
		return flag;
	}

	public static boolean IsDisplay(WebDriver driver, WebElement Obj) {
		boolean flag = false;
		try {
			if (Obj.isDisplayed()) {
				flag = true;
				test.log(LogStatus.INFO, "Selected object is displayed");
			}

		} catch (Exception e) {

		}
		return flag;

	}
	
	public static boolean Click(WebDriver driver, WebElement Obj, String ObjName) {
		boolean flag = false;
		try {
			if (IsDisplay(driver,Obj)) {
				Obj.click();
				flag = true;
				test.log(LogStatus.INFO, "Clicked on " +ObjName);
			}

		} catch (Exception e) {

		}
		return flag;

	}
	
	public static boolean SendKeys(WebDriver driver, WebElement Obj, String Value, String ObjName) {
		boolean flag = false;
		try {
			if (IsDisplay(driver,Obj)) {
				Obj.clear();
				Obj.sendKeys(Value);
				flag = true;
				test.log(LogStatus.INFO, "Entered Text in  " +ObjName);
			}

		} catch (Exception e) {

		}
		return flag;

	}
	
	public static boolean HoverandClick(WebDriver driver, WebElement Obj,WebElement Obj2, String ObjName) {
		boolean flag = false;
		try {
			if (IsDisplay(driver,Obj)) {
				Actions action = new Actions(driver);
				action.moveToElement(Obj).perform();
				action.moveToElement(Obj2).click().build().perform();
				flag = true;
				test.log(LogStatus.INFO, "Mouse hover and Clicked on " +ObjName);
			}

		} catch (Exception e) {

		}
		return flag;
	}
	
	public static boolean GetText(WebDriver driver, WebElement Obj, String ExpectedValue, String ObjName) {
		boolean flag = false;
		try {
			if (IsDisplay(driver,Obj)) {
				String GetTextOfField =Obj.getText();
				System.out.println("GetText Value :" +GetTextOfField);
				
				if(GetTextOfField.equalsIgnoreCase(GetTextOfField)) {
				flag = true;
				test.log(LogStatus.INFO, "get Text of Element " +GetTextOfField);
			}else {
				System.out.println(" [ Actual GetText Value :] " +GetTextOfField+ "[ Expected Get Text Value  :] " +ExpectedValue);
			}
			}
		} catch (Exception e) {

		}
		return flag;

	}
	
	public static boolean ScrollIntoView(WebDriver driver, WebElement Obj, String ObjName) {
		boolean flag = false;
		try {
			if (IsDisplay(driver,Obj)) {
				JavascriptExecutor je = (JavascriptExecutor) driver;
				je.executeScript("arguments[0].scrollIntoView(true);",Obj);
				flag = true;
				test.log(LogStatus.INFO, "Scroll the webpage " +ObjName);
			}

		} catch (Exception e) {

		}
		return flag;
	}
	
	public static boolean SwitchToIframe(WebDriver driver, WebElement obj, String ObjName) {
		boolean flag = false;
		try {
			driver.switchTo().frame(obj);
			flag=true;
		} catch (Exception e) {

		}
		return flag;
	}
	
	public static boolean RemoveFromIframe(WebDriver driver, WebElement obj, String ObjName) {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag=true;
		} catch (Exception e) {

		}
		return flag;
	}
	public static WebElement find(WebDriver driver, By locator) {
		logger.debug("Finding on element: " + locator.toString());
		return driver.findElement(locator);
	}
	
	public static void selectByOptionText(WebDriver driver, String optionText, WebElement dropdownElement) {
		Select dropdown = new Select(dropdownElement);
		logger.debug("Selecting option from dropdown by visible text: " + optionText);
		dropdown.selectByVisibleText(optionText);
	}

	public static boolean RemoveFromIframe(WebDriver driver)
	{
		driver.switchTo().defaultContent();
		return true;
	}

}
