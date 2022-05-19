package pageobjects;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;


public class BasePageObject {
    protected WebDriver driver;

    public BasePageObject(WebDriver driver){
        this.driver = driver;
    }

    public void visit(String url){
        if(url != null) {
            driver.get(url);
        } else {
            throw new RuntimeException("No URL defined");
        }
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public boolean waitForIsDisplayed(By locator, int timeout){
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeout > 0 ? timeout : null));
        } catch (TimeoutException exception){
            System.out.println(locator+" is not visible");
            return false;
        }
        System.out.println(locator+ " is visible ");

        return true;
    }

    public void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(condition);
    }

    public int getScrollHeight(){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        long height = (long) js.executeScript( "return document.querySelector('html').scrollHeight");
        return (int) height;
    }

    public int getWidth(){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        long width = (long) js.executeScript( "return document.querySelector('html').clientWidth");
        return (int) width;
    }

    public void goToBottom(){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript( "var x = document.querySelector('html').scrollHeight; window.scrollTo(0,x);");
    }
}
