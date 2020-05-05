package com.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utils.Utils;

public class ReportManager {
	
	private static ExtentReports extent;

	/**
	 * Get object of ExtentReports
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	/**
	 * Created object of extent report
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ExtentReports createInstance() {
		String workingDir = System.getProperty("user.dir");
		String reportName = "Report\\APITest_Report_" + Utils.getDateTime() + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(workingDir + "\\"+reportName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("API Test Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("API Test report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("os", System.getProperty("os.name"));

		return extent;
	}
}
