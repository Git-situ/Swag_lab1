package com.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	public void startReport() {
		
//		String reportName;

		htmlReporter = new ExtentSparkReporter(".//test-output/"+"reportName"+".html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//add environment details 
		reports.setSystemInfo("Machine", "HP");
		
		//configuration to change look and feel
		htmlReporter.config().setDocumentTitle("Sauce Lab Demo");
		htmlReporter.config().setReportName("Sauce Labs Report");
		
		
	}

}
