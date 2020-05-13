package com.qa.Base;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.provision.prop.ConfigProperties;

public class ProvisionWebdriver {

	private static final Logger log = Logger.getLogger(ProvisionWebdriver.class);
	private static final String browser = null;
	private static ProvisionWebdriver webDriver;
	private WebDriver driver;
	private ConfigProperties appproperties = ConfigProperties.getInstance();

	private ProvisionWebdriver(String Browser) {
		initializeeWebDriver(browser);
	}

	private ProvisionWebdriver(String browser, boolean useGrid) {
		initializeeWebDriver(browser, useGrid);
	}

	public static ProvisionWebdriver getInstance(String browser) {
		if (webDriver == null) {
			webDriver = new ProvisionWebdriver(browser);
		}
		return webDriver;
	}

	public static ProvisionWebdriver getInstance(String browser, boolean useGrid) {
		if (webDriver == null) {
			webDriver = new ProvisionWebdriver(browser, useGrid);
		}
		return webDriver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void releaseDriver() {
		driver.quit();
		webDriver = null;
	}

	public void initializeeWebDriver(String browser) {
		String grid = appproperties.getProperty("useGrid");
		boolean isGrid = "true".equals(grid);
		initializeeWebDriver(browser, isGrid);
	}

	public void initializeeWebDriver(String browser, boolean useGrid) {
		log.info("Test Started for " + browser);
		String property = "grid";
		if ("Firefox-Linux".equalsIgnoreCase(browser)) {
			driver = createFireFoxDriver(useGrid, property, Platform.LINUX);
		} else if ("Firefox-Vista".equalsIgnoreCase(browser)) {
			driver = createFireFoxDriver(useGrid, property, Platform.VISTA);
		} else if ("Firefox-Win7".equalsIgnoreCase(browser)) {
			driver = createFireFoxDriver(useGrid, property, Platform.WINDOWS);
			System.out.println("driver value" + driver);
		} else if ("Firefox-Win8".equalsIgnoreCase(browser)) {
			driver = createFireFoxDriver(useGrid, property, Platform.WINDOWS);
		} else if ("Chrome-Mac".equalsIgnoreCase(browser)) {
			driver = createChromeDriver(useGrid, property, Platform.MAC);
		} else if ("Chrome-Win8".equalsIgnoreCase(browser)) {
			driver = createChromeDriver(useGrid, property, Platform.WINDOWS);
		} else if ("Chrome-Win7".equalsIgnoreCase(browser)) {

			driver = createChromeDriver(useGrid, property, Platform.WINDOWS);
		} else if ("IE-Win7".equalsIgnoreCase(browser)) {
			driver = createIEDriver(useGrid, property);
		} else if ("IE-Win8".equalsIgnoreCase(browser)) {
			driver = createIEDriver(useGrid, property);
		} else if ("Safari-Mac".equalsIgnoreCase(browser)) {
			DesiredCapabilities caps = DesiredCapabilities.safari();
			driver = createDriver("safari", appproperties.getProperty(property), Platform.MAC, caps);
		} else {
			// If no browser passed throw exception
			log.error("Browser is incorrect");
		}
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(appproperties.getProperty("baseUrl"));
			driver.manage().window().maximize();
		}
	}

	private WebDriver createIEDriver(boolean isGrid, String urlProperty) {
		if (isGrid) {
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			return createDriver("internet explorer", appproperties.getProperty(urlProperty), Platform.WINDOWS, caps);
		} else {
			System.setProperty("webdriver.ie.driver", appproperties.getProperty("ieLib"));
			DesiredCapabilities dc = getDesiredCapabilities(true);
			return new InternetExplorerDriver(dc);
		}
	}

	private WebDriver createFireFoxDriver(boolean isGrid, String urlProperty, Platform platform) {
		if (isGrid) {
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			return createDriver("firefox", appproperties.getProperty(urlProperty), platform, caps);
		}

		else {

			// System.setProperty("webdriver.gecko.driver","D:\\Swadhinworkspace3\\Quad-tool-functional-test\\Browser\\geckodriver.exe");

			DesiredCapabilities dc = getDesiredCapabilities(false);
			System.out.println("value of dc" + dc);
			return new FirefoxDriver(dc);

			// DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			// capabilities.setCapability("marionette", true);
			// System.out.println("value of dc" + capabilities);
			// return new FirefoxDriver( capabilities );

		}
	}

	private WebDriver createChromeDriver(boolean isGrid, String urlProperty, Platform platform) {
		if (isGrid) {
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			return createDriver("chrome", appproperties.getProperty(urlProperty), platform, caps);
		} else {

			System.setProperty("webdriver.chrome.driver", appproperties.getProperty("chromeLib"));
			DesiredCapabilities dc = getDesiredCapabilities(false);
			return new ChromeDriver(dc);
		}
	}

	private WebDriver createDriver(String browserName, String driverUrl, Platform platform, DesiredCapabilities caps) {
		caps.setBrowserName(browserName);
		caps.setPlatform(platform);
		try {
			return new RemoteWebDriver(new URL(driverUrl), caps);
		} catch (Exception e) {
			log.error("Malformed URL exception for Hub Url " + e.getMessage());
		}
		return null;
	}

	private DesiredCapabilities getDesiredCapabilities(boolean isIe) {
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		dc.setCapability("ignoreZoomSetting", true);
		dc.setCapability("ignoreProtectedModeSettings", true);
		dc.setJavascriptEnabled(true);

		if (isIe) {
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

		}

		// open a new clean session with all cookies and cache being cleared
		dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		try {
			Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
		} catch (IOException e) {
			log.error("IOException " + e.getMessage());
		}
		return dc;
	}

}
