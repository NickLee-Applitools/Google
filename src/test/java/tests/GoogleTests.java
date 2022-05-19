package tests;

import com.applitools.eyes.selenium.Eyes;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.Google;

public class GoogleTests extends BaseTest {

    private Google google;

    @BeforeMethod
    public void setUp(){
        google = new Google(driver,eyesManager.getEyes());
    }

    @Test
    public void homePage() {
        google.changeBackgroundColor();
        google.checkHomePage();
    }
}
