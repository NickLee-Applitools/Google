package pageobjects;

import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.applitools.eyes.selenium.fluent.Target;

public class Google extends BasePageObject {
    Eyes eyes;
    private static final String url = "https://google.com/";

    public Google(WebDriver driver, Eyes eyes) {
        super(driver);
        super.visit(url);
        this.eyes = eyes;

    }

    public void checkHomePage()  {
        eyes.check("Home  Page",
                Target.window()
                        .fully(true)
        );
    }

    public void changeBackgroundColor(){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("document.getElementsByTagName(\"body\")[0].style.backgroundColor = \"yellow \";");
    }
}
