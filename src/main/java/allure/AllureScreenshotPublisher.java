package allure;

import helpers.LogHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import utils.WebDriverUtil;

import static io.qameta.allure.model.Status.PASSED;
import static io.qameta.allure.model.Status.SKIPPED;

public class AllureScreenshotPublisher implements StepLifecycleListener {

    @Override
    public void afterStepUpdate(StepResult result) {
        if (result.getStatus() != PASSED && result.getStatus() != SKIPPED) {
            byte[] screen = WebDriverUtil.getScreenshot();
            String screenshotName = WebDriverUtil.writeScreenshotToFile(screen, result.getName().replaceAll(" ", "_"), null);
            LogHelper.getLogger().error("Step '" + result.getName() + "' FAILED. Screen shot: " + screenshotName);
            Allure.getLifecycle().addAttachment(screenshotName, "image/png", "png", screen);
        }
    }

}
