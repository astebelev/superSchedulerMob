package com.astebelev.superscheduler.tests;

import com.astebelev.superscheduler.framework.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Arrays;


public class TestBase {

    protected static ApplicationManager app =
            new ApplicationManager();
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    @BeforeSuite
    public  void setUp() throws MalformedURLException {
        app.init();
    }

    @AfterSuite
    public void tearDown(){
        app.stop();
    }

    //    ______________________________________________________
    @BeforeMethod
    public void startLogger(Method m, Object[]p)
    {
        logger.info("Start method " + m.getName()+ " with param: "+ Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void stopLogger(Method m){
        logger.info("Stop method " + m.getName() + "\n\t\t\t+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
    }


    public static class MyListener extends AbstractWebDriverEventListener {
        Logger logger = LoggerFactory.getLogger(TestBase.class);



        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            logger.info("Start search element " + by);

        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            long time = System.currentTimeMillis();
            app.makeScreenshot(time);
            logger.error("!!!!!! TEST FAILED !!!!!!", throwable);
            logger.info("Made screenshot screenshot-"+ time + ".png");
        }



    }
}