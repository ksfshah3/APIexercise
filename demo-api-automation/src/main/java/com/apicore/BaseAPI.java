package com.apicore;

import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.Status;
import com.report.ReportTestManager;
import com.utils.Utils;

import io.restassured.RestAssured;

public class BaseAPI {

	@BeforeClass
	public void setup() throws Exception {
		RestAssured.baseURI = Utils.getConfigData("APIUrl");
	}
	
	public void log(String text){
		 ReportTestManager.getTest().log(Status.PASS, text);
	}

}
