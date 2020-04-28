package test.java.Commons;


import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import test.java.PageObject.Login;

import java.net.MalformedURLException;
import java.net.URL;


public class Init {

   public MobileDriver driver;


   public Login login ;
    @BeforeTest
    public void initApp(){
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("app","/Users/abc/Desktop/Leonardo/git/New_7_4/Leonardo_Automation/leonardo_app_react_latest.app");
        capability.setCapability("deviceName","iPhone X");
        capability.setCapability("platformName","iOS");
        capability.setCapability("platformVersion", "12.1");

        capability.setCapability("automationName","XCUITest");
        capability.setCapability("autoGrantPermissions",true);
        capability.setCapability("autoAcceptAlerts",true);
        capability.setCapability("clearSystemFiles", true);
        capability.setCapability("newCommandTimeout",60000);

        try {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capability);
            Reporter.log("Open Application.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        pause(10);
         login = new Login(driver);
    }

    @AfterTest
    public void CloseApp(){
        driver.quit();
        Reporter.log("Close Application.");
    }

    public void pause(int sec) {

        try {
            Thread.sleep(1000*sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
