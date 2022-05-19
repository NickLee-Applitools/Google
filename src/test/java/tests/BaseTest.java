package tests;

import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.visualgrid.model.VisualGridOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import setup.EyesManager;
import com.applitools.eyes.TestResultsSummary;

import java.lang.reflect.Method;



public class BaseTest {
    //private EyesRunner runner;          // instance variable
    EyesManager eyesManager;            // instance var
    //private static BatchInfo batch;     // class variable
    WebDriver driver;                   // instance variable, do i need if on Eyesmanager?

    @BeforeSuite
    public void BeforeSuite(){
    }

    @BeforeTest
    public void beforeTest(){

    }

    @BeforeClass
    public void BeforeClass(){
    }

    @BeforeMethod
    public void BeforeMethod(Method method){
        this.driver = new FirefoxDriver();
        this.eyesManager = new EyesManager(driver);  //added this, create new eyes instance per test

        //any additional settings will need to be set within tests
        Eyes eyes = eyesManager.getEyes(); //   Recommendation: create new Eyes instance per test

        String appName = this.getClass().getName().split("\\.")[1];

        eyes.open(driver, appName, method.getName(), new RectangleSize(1280,671));
        BatchInfo b = new BatchInfo("GoogleBatch");
        /* Set the batch to Eyes */
        b.setSequenceName("GoogleSequenceName");
        eyes.setBatch(b);
    }


    @AfterMethod
    public void afterMethod(){
        eyesManager.tearDown();
    }

    @AfterTest
    public void afterTest(){

        Eyes eyes = eyesManager.getEyes();
        eyes.abortIfNotClosed();
    }

    @AfterClass
    public void afterClass(){
    }


    @AfterSuite
    public void afterSuite(){
        eyesManager.getResults();
    }

}
