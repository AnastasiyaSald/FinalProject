package case01Tests;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener  {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[LISTENER] Test STARTED: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("[LISTENER] Test PASSED: " + result.getName() + " (" + duration + "ms)");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[LISTENER] Test FAILED: " + result.getName());
        System.out.println("   Reason: " + result.getThrowable().getMessage());
        // In Selenium projects, you would take a screenshot here:
        // takeScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[LISTENER] Test SKIPPED: " + result.getName());
    }

}
