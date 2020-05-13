package com.qa.utils;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.Base.TestBase;

public class Utilities extends TestBase {

	private static final Logger logger = Logger.getLogger(Utilities.class);

	public static void WaitForElementPresentByXpath(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public static void WaitForElementPresentBycsspath(WebDriver driver, String csspath) {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(csspath)));
	}

	public static void screenCapture(String path, WebDriver driver1) throws AWTException {
		WebDriver driver = driver1;
		/* capturing Screen Shot */
		try {

			// Defining destination file path
			logger.info("Capturing screenshot at path " + path);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(path));
			logger.info("Captured screenshot at path " + path);
		} catch (IOException e) {
			logger.error("Error taking screen shot " + e.getMessage());
		}
	}

	/*
	 * 
	 * This Function Wait for elements present by BY type it accepts two @parameters
	 * driver, By- String type and waits till element is visible
	 */

	public static void WaitForElementPresentByid(WebDriver driver, String id) {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	public static boolean WindowSwitchto(WebDriver driver) {
		boolean flag = false;
		{
			try {
				for (String handle : driver.getWindowHandles()) {
					driver.switchTo().window(handle);
				}
				Thread.sleep(2000);
				flag = true;
			} catch (Exception e) {

			}
			return flag;

		}
	}

	// Capture Screenshot method

	public static String captureScreenshot(WebDriver driver, String screenshotname) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./CaptureScreenshot_Failed/" + screenshotname + ".png"));
			System.out.println("Screenshot taken");
		} catch (Exception e) {

			System.out.println("exception while taking the screenshot" + e.getMessage());
		}
		return screenshotname;

	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcfile,new File(currentDir + "/Exceptionscreenshots/" + System.currentTimeMillis() + ".png"));

	}

	public static String getscreenshot(WebDriver driver, String sceenshotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/Screenshots/" + sceenshotName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		return destination;

	}

}
