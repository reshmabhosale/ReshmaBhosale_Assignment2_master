package com.qa.Base;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.qa.pages.Sceanrio2;
import com.qa.pages.Sceanrio1;
import com.qa.utils.Utilities;
import com.qa.utils.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestBase {
	private static final Logger logger = Logger.getLogger(TestBase.class);

	public static WebDriver driver = null;
	public static ProvisionWebdriver provisionDriver = null;
	public static com.qa.provision.prop.ConfigProperties configProperties = null;
	public static Boolean isInitialized = false;
	public static boolean isSkip = false;
	public static boolean isFail = false;
	public static boolean isPass = false;
	public static int count = -1;
	public static int rownum = -1;

	public static Method method[] = null;

	public static ExtentReports extent = null;

	public static ExtentTest test = null;

	public static Object data[][] = null;

	public static Properties prop;
	public static Properties prop1;

	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListner;
	
	protected  Sceanrio1 Sceanrio1obj ;
	protected  Sceanrio2 Sceanrio2obj;
	
	static {
		initialize();
	}

	@BeforeSuite
	public static void initializeSuite() throws MalformedURLException {
		/* Reading data from config.properties text file */
		configProperties = com.qa.provision.prop.ConfigProperties.getInstance();
	}

	public static void initialize() {
		if (isInitialized == false) {
			try {
				extent = com.qa.provision.prop.ExtentManager.getInstance();
				// extent.loadConfig(new File(extentConfigFilepath));
				/* Loading XLS_Reader Functions */
				logger.debug("Loading suites ...");

				logger.debug("Complete loading data for tests ...");
				isInitialized = true;
			} catch (Exception e) {
				logger.error("Error reading test suite data " + e.getMessage());
			}
		}
	}

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 10000);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public static void initializeDriver() {
		String browserType = configProperties.getProperty("browserType");
		System.out.println("browser" + browserType);
		boolean useGrid = Boolean.parseBoolean(configProperties.getProperty("useGrid"));
		provisionDriver = ProvisionWebdriver.getInstance(browserType, useGrid);
		System.out.println("provision webdriver" + provisionDriver);
		driver = provisionDriver.getDriver();
		System.out.println("driver value" + driver);
		System.out.println("driver launched");

	}

	public static void openBrowser() {
		try {
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void closeBrowser() {
		driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		// extent.flush();
		extent.close();
	}

	@BeforeMethod
	protected void beforeMethod(Method method) {
		test = extent.startTest(this.getClass().getSimpleName() + " :: " + method.getName());

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException, AWTException, InterruptedException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test failed " + result.getThrowable());
			String screenshotFailPath = Utilities.getscreenshot(driver, result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotFailPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped ");
		} else {

			String screenshotPassPath = Utilities.getscreenshot(driver, result.getName());
			test.log(LogStatus.PASS, test.addScreenCapture(screenshotPassPath));
		}

	//	driver.quit();
		extent.endTest(test);
		extent.flush();
	}

	public static void screenCapture(String path) {

		/* capturing Screen Shot */
		try {
			logger.info("Capturing screenshot at path " + path);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(path));
			logger.info("Captured screenshot at path " + path);
		} catch (IOException e) {
			logger.error("Error taking screen shot " + e.getMessage());
		}
	}

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(".\\Configs\\config.properties");
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void loadCommonSetting() throws IOException

	{
		String envm = prop.getProperty("test_environment");
		System.out.println("env " + envm);
		if (envm.equals("stg")) {
			prop1 = new Properties();
			FileInputStream fis = new FileInputStream(".\\Configs\\stg.properties");
			prop1.load(fis);
            System.out.println(prop.getProperty("test_environment") + ".properties file has been loaded successfully!");
		} else if (envm.equals("qa")) {
			prop1 = new Properties();
			FileInputStream fis = new FileInputStream(".\\Configs\\qa.properties");
			prop1.load(fis);
			System.out.println(prop.getProperty("test_environment") + ".properties file has been loaded successfully!");
		}

	}

	public static void initialization() {
		String browserName = prop.getProperty("browserType");
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\Browser\\geckodriver_26.exe");

			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", ".\\Browser\\chromedriver_78.exe");
			driver = new ChromeDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListner = new WebEventListener();
		e_driver.register(eventListner);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		driver.get(prop1.getProperty("baseUrl"));
	}
	
	

}
