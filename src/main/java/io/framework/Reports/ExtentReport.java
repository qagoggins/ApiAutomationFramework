package io.framework.Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.Getter;
import lombok.Setter;

public final class ExtentReport {
    private ExtentReport() {}
    private static ExtentReports extent;
    private static ExtentTest test;


    public static void initReports() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("report/index.html");
        extent.attachReporter(spark);
    }
    public static void tearDownReports() {
        extent.flush();
    }
    public static void createTest(String name) {
        test = extent.createTest(name);
        ExtentManager.setTest(test);
    }

    public static void addAuthor(String[] authors) {
        for(String author:authors) {
            ExtentManager.getTest().assignAuthor(author);
        }
    }
    public static void addCategory(String[] categories) {
        for(String category:categories) {
            ExtentManager.getTest().assignCategory(category);
        }
    }

}
