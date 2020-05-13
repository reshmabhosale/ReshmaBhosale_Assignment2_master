package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.Base.TestBase;
import com.qa.pages.Sceanrio1;
import com.relevantcodes.extentreports.LogStatus;

public class Sceanrio1Test extends TestBase {

	
	public Sceanrio1Test() {
		super();
	}

	@BeforeMethod
	public  void setup() throws Exception {

		loadCommonSetting();
		initialization();
		Sceanrio1obj = new Sceanrio1();
	}

	@Test(priority = 1)
	public void verifyHomePageTitle() throws InterruptedException {
		try {
			test.log(LogStatus.INFO, "TC_01 : Create Account under Sign In.");
			Sceanrio1obj.VerifyPageTitle();
			Sceanrio1obj.CreateNewAccount();
			Assert.assertEquals(test.getRunStatus(), LogStatus.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
