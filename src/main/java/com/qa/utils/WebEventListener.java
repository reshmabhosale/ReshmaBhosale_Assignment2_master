package com.qa.utils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.qa.Base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {
	
	public void beforeNavigateTo(String url,WebDriver driver)
	{
		System.out.println("Before navigating to: '"+url+"'");
	}

	
	public void beforeAlertAccept(WebDriver driver) {
	
		
	}

	
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeNavigateBack(WebDriver driver) {
		
		System.out.println("navigating back to previous page");
		
	}


	public void afterNavigateBack(WebDriver driver) {
		
		
		
	}

	
	public void beforeNavigateForward(WebDriver driver) {
		
		System.out.println("navigating  back to previous page");
	}

	
	public void afterNavigateForward(WebDriver driver) {
		
		System.out.println("navigating  forward to next page");
	}

	
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		
		System.out.println("trying to find Element By :"+by.toString());
		
	}

	
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
	
		System.out.println("Found Element By :"+by.toString());
		
	}

	
	public void beforeClickOn(WebElement element, WebDriver driver) {
	  System.out.println("Trying to click on:"+element.toString());
		
	}


	public void afterClickOn(WebElement element, WebDriver driver) {
		
		 System.out.println("Trying to click on:"+element.toString());
	}

	
	

	
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occured: "+error);
		try
		{
			Utilities.takeScreenshotAtEndOfTest();
		}
		catch(Exception e)
		{
			
		}
		
		
	}


	
	public void beforeChangeValueOf(WebElement element, WebDriver driver,
			CharSequence[] keysToSend) {
		
		
	}


	
	public void afterChangeValueOf(WebElement element, WebDriver driver,
			CharSequence[] keysToSend) {
		
		
	}


	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	




}
