package com.qa.provision.prop;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			prepareExtent();
		}
		return extent;
	}

	private static void prepareExtent() {
		extent = new ExtentReports(".//Report//extentreport.html", Boolean.TRUE, NetworkMode.ONLINE);
		extent.config().documentTitle("Automation Practice ").reportName("Regression")
				.reportHeadline("Automation PracticeTest Report");
	}

}
