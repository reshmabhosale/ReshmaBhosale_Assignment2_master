package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.utils.BrowserActions;

public class Sceanrio2 extends TestBase{

	@FindBy(css = "a.login")
	public static WebElement SignInButton;
	
	@FindBy(css = "input#email")
	public static WebElement EnterEmailID;

	@FindBy(css = "input#passwd")
	public static WebElement EnterPassword;

	@FindBy(css = "button#SubmitLogin")
	public static WebElement SubmitButton;

	
	@FindBy(css = "a[title='Women']")
	public static WebElement WomenSection;

	@FindBy(css = "img[src='http://automationpractice.com/img/p/1/1-home_default.jpg']")
	public static WebElement SelectImage;

	@FindBy(xpath = "(//span[contains(text(),'Quick view')])[1]")
	public static WebElement QuickView;
	
	@FindBy(css = "input#quantity_wanted")
	public static WebElement GetTextOfQt;

	@FindBy(css = "i.icon-plus")
	public static WebElement AddQT;

	@FindBy(css = "button[name='Submit']")
	public static WebElement AddToCart;
	
	@FindBy(css = "i.icon-ok")
	public static WebElement VerifyRightIcon;

	@FindBy(css = "a[title='Proceed to checkout']>span")
	public static WebElement ProceedToCheckout;
	
	@FindBy(css = "td#total_price_container")
	public static WebElement GetTotalCost;
	
	@FindBy(css = "textarea.form-control")
	public static WebElement AddaComment;
	
	@FindBy(css = "button[name='processAddress']")
	public static WebElement ProceedToCheckout2;
	
	@FindBy(css = "input#cgv")
	public static WebElement SelectTermCheckbox;

	@FindBy(css = "button[name='processCarrier']")
	public static WebElement ProceedToCheckout3;
	
	@FindBy(css = "span#total_price")
	public static WebElement GetTextofAmount;
	
	@FindBy(css = "a[title='Pay by bank wire']")
	public static WebElement SelectPayByBankWire;
	
	@FindBy(css = ".//button[@class='button btn btn-default button-medium']")
	public static WebElement IConfirmMyOrder;
	
	@FindBy(css = "div.box")
	public static WebElement GetTextOfOrderConfirmation;
	
	@FindBy(css = ".//p[@class='cheque-indent']/strong")
	public static WebElement PaymentConfirmation;
	
	//-----------------//
	@FindBy(css = "a.account")
	public static WebElement ClickOnProfile;
	
	@FindBy(css = "a[title='Orders']")
	public static WebElement orders;
	
	@FindBy(css = "td[class='history_price']")
	public static WebElement TotalPrice;
	
	@FindBy(css = "iframe.fancybox-iframe")
	public static WebElement IframeID;
	
	@FindBy(css = "a[class='button btn btn-default standard-checkout button-medium']")
	public static WebElement ProceedToCheckout_Amountveify;
	
	public Sceanrio2() {
		PageFactory.initElements(driver, this);
	}
	
	public void LoginWithNewCredentials() throws Exception
	{
		BrowserActions.Click(driver, SignInButton, "Sign In button");
		BrowserActions.SendKeys(driver, EnterEmailID,prop1.getProperty("EmailID"), "email ID");
		BrowserActions.SendKeys(driver, EnterPassword,prop1.getProperty("Password"), "Password");
		BrowserActions.Click(driver, SubmitButton, "Submit button");
	}
	public void AddToCardItems() throws Exception
	{
		BrowserActions.Click(driver, WomenSection, "Submit button");
		BrowserActions.ScrollIntoView(driver, SelectImage, "QuickView");
		BrowserActions.HoverandClick(driver, SelectImage,QuickView, "Quick View option");
		Thread.sleep(2000);
		BrowserActions.SwitchToIframe(driver, IframeID, "iframe");
		BrowserActions.Click(driver, AddQT, "add Quanitity button");
		BrowserActions.Click(driver, AddQT, "add Quanitity button");
		BrowserActions.Click(driver, AddToCart, "AddToCart button");
		BrowserActions.RemoveFromIframe(driver);
	}
	
	public void paymentprocess() throws Exception
	{
		BrowserActions.Click(driver, ProceedToCheckout, "ProceedToCheckout button");
		BrowserActions.GetText(driver, GetTotalCost, prop1.getProperty("ExpectedAmount"), "Amount");
		BrowserActions.Click(driver, ProceedToCheckout_Amountveify, "Proceed to checkout button");
		BrowserActions.SendKeys(driver, AddaComment,prop1.getProperty("AddaComment"), "AddaComment");
		BrowserActions.Click(driver, ProceedToCheckout2, "Proceed to checkout button");
		BrowserActions.Click(driver, SelectTermCheckbox, "Select checkbox");
		BrowserActions.Click(driver, ProceedToCheckout3, "Proceed to checkout button");
		BrowserActions.GetText(driver, GetTextofAmount, prop1.getProperty("ExpectedAmount"), "Amount");
		BrowserActions.Click(driver, SelectPayByBankWire, "Select Pay By Bank Wire option");
		BrowserActions.Click(driver, IConfirmMyOrder, "IConfirmMyOrder");
		BrowserActions.Click(driver, PaymentConfirmation, "Payment Confirmation");
	}
	
	public void OrderHistory() throws Exception
	{
		BrowserActions.Click(driver, ClickOnProfile, "Click On Profile option");
		BrowserActions.Click(driver, orders, "Order History button");
		BrowserActions.GetText(driver, TotalPrice, prop1.getProperty("ExpectedAmount"), "Amount");
		
	}


}
