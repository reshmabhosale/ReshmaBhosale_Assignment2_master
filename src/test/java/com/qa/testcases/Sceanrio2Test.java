package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.pages.Sceanrio2;
import com.relevantcodes.extentreports.LogStatus;

public class Sceanrio2Test extends TestBase {
	
	public Sceanrio2Test() {
		super();
	}

	@BeforeMethod
	public  void setup() throws Exception {
		loadCommonSetting();
		initialization();
		Sceanrio2obj = new Sceanrio2();
	}

	@Test(priority = 1)
	public void verifyHomePageTitle() throws InterruptedException {
		try {
			test.log(LogStatus.INFO, "Verify amount");
			Sceanrio2obj.LoginWithNewCredentials();
			Sceanrio2obj.AddToCardItems();
			Sceanrio2obj.paymentprocess();
			Sceanrio2obj.OrderHistory();
			Assert.assertEquals(test.getRunStatus(), LogStatus.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
