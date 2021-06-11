
package com.astebelev.superscheduler.framework;

import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    AppiumDriver wd;
    UserHelper user;


    public void init() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("deviceName","nex5");
        caps.setCapability("platformVersion","11.0");
        caps.setCapability("appPackage","com.example.svetlana.scheduler");
        caps.setCapability("appActivity",".presentation.splashScreen.SplashScreenActivity");
        caps.setCapability("app","D:\\GitHub\\superSchedulerMob\\src\\test\\resources\\v.0.0.3.apk");

        wd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        user = new UserHelper(wd);
    }

    public void stop() {
        wd.quit();
    }

    public UserHelper getUser() {
        return user;
    }

    public String getCurrentURL() {
        return wd.getCurrentUrl();
    }

    public void goURL(String url) {
        wd.get(url);
    }

    public void makeScreenshot(long time) {
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("Screenshots/screenshot-" + time + ".png");

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}