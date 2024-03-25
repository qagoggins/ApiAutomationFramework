package io.framework.Listeners;

import io.framework.Annotations.FrameworkAnnotation;
import io.framework.Reports.ExtentLogger;
import io.framework.Reports.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();

    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.tearDownReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
        String[] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author();
        ExtentReport.addAuthor(authors);
        String[] categories = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category();
        ExtentReport.addCategory(categories);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName() + " is passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(String.valueOf(result.getThrowable()));
    }
}
