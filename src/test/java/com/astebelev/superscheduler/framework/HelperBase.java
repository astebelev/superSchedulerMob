package com.astebelev.superscheduler.framework;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
    AppiumDriver wd;

    public HelperBase(AppiumDriver wd) {

        this.wd = wd;
    }

    public void type(By locator, String text) {
        if (text != null){
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }

    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {

        return wd.findElements(locator).size() > 0;
    }
    public boolean isElementPresentWait(By locator){
        try{
            new WebDriverWait(wd,20).until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        }catch (Exception ex){
            return false;
        }

    }




}
