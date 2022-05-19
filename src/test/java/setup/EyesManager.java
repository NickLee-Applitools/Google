package setup;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import com.applitools.eyes.TestResultsSummary;


public class EyesManager {
    private WebDriver driver;
    private String appName;
    private EyesRunner runner;
    private Eyes eyes;
    private static BatchInfo batchinfo; // share batch object for each stte
    private boolean useVisualGrid = false;

    public EyesManager(WebDriver driver){
        this.driver = driver;

        Configuration configuration = new Configuration();
        configuration.setApiKey("");

        if(useVisualGrid) {
            runner = new VisualGridRunner(); // share runner across tests
            configuration.addBrowser(new DesktopBrowserInfo(1400, 1200, BrowserType.CHROME));
            configuration.addBrowser(new DesktopBrowserInfo(1280, 671, BrowserType.FIREFOX)); //1440, 900,configuration.addBrowser(new DesktopBrowserInfo(1280, 671, BrowserType.SAFARI));
            configuration.addBrowser(new DesktopBrowserInfo(1280  , 1024, BrowserType.EDGE_CHROMIUM));
            // configuration.addBrowser(new IosDeviceInfo(IosDeviceName.iPhone_X, ScreenOrientation.LANDSCAPE));

        } else {
            runner = new ClassicRunner();
        }
        this.eyes = new Eyes(runner);

        if (batchinfo == null){ //batchInfo is a shared
             batchinfo = new BatchInfo("Google Batch 1");

            batchinfo.setNotifyOnCompletion(true);
        }

        configuration.setBatch(batchinfo);
        configuration.setAppName("Google");
        configuration.setHideCaret(true);
        configuration.setHideScrollbars(true);
        //configuration.setVisualGridOptions(new VisualGridOption("chromeHeadless",false));

        eyes.setConfiguration(configuration);
        System.out.println(eyes.getConfiguration());
    }

    public void setAppName(String name){
        this.appName = name;
    }

    public void openEyes(String testName){
        eyes.open(this.driver,appName, testName);
    }

    public Eyes getEyes(){
        return this.eyes;
    }

    public void tearDown(){
        eyes.closeAsync();
        this.driver.quit();
    }

    public void getResults(){
        TestResultsSummary results = runner.getAllTestResults();
        System.out.println("Results: "+ results);
    }

    public void setBatchName(String name){ // batch names are used to define test suite
        eyes.setBatch(new BatchInfo(name));
    }

    public boolean getSseVisualGrid(){
        return  this.useVisualGrid;
    }

}


