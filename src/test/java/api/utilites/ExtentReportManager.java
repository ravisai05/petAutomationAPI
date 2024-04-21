package api.utilites;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports report;
	public ExtentTest test;
	String repName;
		public void onStart(ITestContext context) {
			// TODO Auto-generated method stub
			String TimeFormat=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName="Test-API"+TimeFormat+".html";
			sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specfiy location
			sparkReporter.config().setDocumentTitle("API-Testing");
			sparkReporter.config().setReportName("Pet STore API");
			sparkReporter.config().setTheme(Theme.DARK);
			
			report=new ExtentReports();
			report.attachReporter(sparkReporter);
			report.setSystemInfo("Application", "Pet Store API");
			report.setSystemInfo("OS", "WIN 10 Pro 22H2");
			report.setSystemInfo("UserName", System.getProperty("user.name"));
			report.setSystemInfo("user", "test");
			
			
		}
		
		public void onTestSuccess(ITestResult result) {
			// TODO Auto-generated method stub
			test=report.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.PASS,"Test Passed");
		}
		
		public void onTestFailure(ITestResult result) {
			// TODO Auto-generated method stub
			test=report.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.FAIL, "Test Failed");
			test.log(Status.FAIL, result.getThrowable().getMessage());
			
		}
		
		public void onTestSkipped(ITestResult result) {
			test=report.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.SKIP, "Test-Skipped");
			test.log(Status.SKIP, result.getThrowable().getMessage());
		}
		public void onFinish(ITestContext context) {
			// TODO Auto-generated method stub
			report.flush();
		}
	
}
