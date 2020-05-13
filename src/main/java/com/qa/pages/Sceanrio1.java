package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.utils.BrowserActions;


public class Sceanrio1 extends TestBase {
	int timeout = 20;

	@FindBy(css = "a.login")
	public static WebElement SignInButton;

	@FindBy(css = "input#email_create")
	public static WebElement EnterEmailId;

	@FindBy(css = "button#SubmitCreate")
	public static WebElement CreateNewAccount;
	
	@FindBy(css = "input#id_gender2")
	public static WebElement SelectMrs;
	
	@FindBy(css="input#customer_firstname")
	public static WebElement FirstName;

	@FindBy(css="input#customer_lastname")
	public static WebElement LastName;
	
	@FindBy(css="input#passwd")
	public static WebElement Password;
	
	@FindBy(css="select#days")
	public static WebElement SelectDate;
	
	@FindBy(css="select#months")
	public static WebElement SelectMonths;
	
	@FindBy(css="select#years")
	public static WebElement SelectYear;
	
	@FindBy(css="input#company")
	public static WebElement Company;
	
	@FindBy(css="input#address1")
	public static WebElement Address;
	
	@FindBy(css="input#city")
	public static WebElement City;
	
	@FindBy(css="select#id_state")
	public static WebElement State;
	
	@FindBy(css="input#postcode")
	public static WebElement Zipcode;
	
	@FindBy(css="input#phone_mobile")
	public static WebElement MobileNo;
	
	@FindBy(css="button#submitAccount")
	public static WebElement RegisterButton;
	
	@FindBy(css="a[title='Home']")
	public static WebElement Homebutton;
	
	public Sceanrio1() {
		PageFactory.initElements(driver, this);
	}
	
	public void VerifyPageTitle() throws Exception
	{
		BrowserActions.VerifyPageTitle(prop1.getProperty("title".trim()));
		BrowserActions.Click(driver, SignInButton, "Sign In Button");
		BrowserActions.VerifyPageTitle(prop1.getProperty("AfterSignInPageTitle".trim()));
	}

	public void CreateNewAccount()
	{
		BrowserActions.SendKeys(driver, EnterEmailId,prop1.getProperty("EmailID"), "email ID");
		BrowserActions.Click(driver, CreateNewAccount, "Create New Account button");
		BrowserActions.Click(driver, SelectMrs, "Mrs option");
		BrowserActions.SendKeys(driver, FirstName,prop1.getProperty("FirstName"), "FirstName");
		BrowserActions.SendKeys(driver, LastName,prop1.getProperty("LastName"), "LastName");
		BrowserActions.SendKeys(driver, Password,prop1.getProperty("Password"), "Password");
		BrowserActions.SendKeys(driver, Company,prop1.getProperty("Company"), "Company");
		BrowserActions.SendKeys(driver, Address,prop1.getProperty("Address"), "Address");
		BrowserActions.SendKeys(driver, City,prop1.getProperty("City"), "City");
		BrowserActions.selectByOptionText(driver, prop1.getProperty("State"), State);
		BrowserActions.SendKeys(driver, Zipcode,prop1.getProperty("Zipcode"), "Zipcode");
		BrowserActions.SendKeys(driver, MobileNo,prop1.getProperty("mobNo"), "MobileNo");
		BrowserActions.Click(driver, RegisterButton, "Register");
		BrowserActions.Click(driver, Homebutton, "Homebutton");
	}
	

	

}
