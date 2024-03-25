package io.framework.demo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.Test;

public class ExtentReportsDemo {

    @Test
    public void extentTest() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
        extent.attachReporter(spark);

        ExtentTest extentTest = extent.createTest("test1");
        extentTest.pass("Test created");
        extent.flush();
    }
}
