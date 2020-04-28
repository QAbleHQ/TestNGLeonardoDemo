package test.java.PageObject;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import test.java.Commons.Init;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class Login {

    MobileDriver driver;
    protected static Wait<WebDriver> wait;

    public Login(MobileDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }




    String emailTextbox = "(//XCUIElementTypeOther[@name='Username'])[2]/XCUIElementTypeTextField";

    String passwordTextBox = "//XCUIElementTypeOther[@name='Password']/XCUIElementTypeSecureTextField";

    String loginBnt = "//XCUIElementTypeOther[@name='Login']";

    String progressBar_Icon="//XCUIElementTypeOther[@name='In progress']";

    String screen="(//XCUIElementTypeOther[@name='We are happy to announce that the Leonardo247 app has been updated with new features and navigation. We have included these helpful overlays to show you what has changed. Tap anywhere to continue.'])[2]";

    String otherScreen="(//XCUIElementTypeOther[@name='Navigate to the Task List. New options to apply filters and manage settings. Tapping on a property pin will display metrics and allow you to navigate to the Task List.'])[2]";

    String dashboard ="//XCUIElementTypeStaticText[@name='Dashboard']";

    String filterIcon="(//XCUIElementTypeOther[@name='Dashboard'])[5]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther[1]";

    String sideMenu ="(//XCUIElementTypeOther[@name='Dashboard'])[5]/XCUIElementTypeOther[1]/XCUIElementTypeOther";

    String guidance_Screen = "(//XCUIElementTypeOther[@name='Navigate to the Dashboard. Toggle between task types. Tasks are grouped by category. Tapping will collapse the group. Swipe left to display options to complete a task, set it to cannot do, or snooze. Swipe right to display options to comment or assign task.'])[3]";

    String unit_Tasks_Tab= "((//XCUIElementTypeOther[@name='DD Test Property 14 Tasks'])[1]/following::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3])[1]";

    public void cannotDoUnitTasksFunctionality()
    {
        driver.findElement(By.xpath(emailTextbox)).sendKeys("ddtester14@leonardo.com");
        driver.findElement(By.xpath(passwordTextBox)).sendKeys("sagar@123");
        driver.findElement(By.xpath(loginBnt)).click();
        Reporter.log("Login successfully on Application.");
        pause(5);
        pauseElementDisappear(driver.findElement(By.xpath(progressBar_Icon)));
        driver.findElement(By.xpath(screen)).click();
        pause(1);
        driver.findElement(By.xpath(otherScreen)).click();
        pause(1);
        driver.findElement(By.xpath(dashboard)).isDisplayed();
        Reporter.log("Verify Dashboard page is displayed");
        driver.findElement(By.xpath(filterIcon)).isDisplayed();
        driver.findElement(By.xpath(sideMenu)).click();
        Reporter.log("Click on side Menu");
        pause(2);
        driver.findElement(By.xpath(guidance_Screen)).click();
        pause(2);
        driver.findElement(By.xpath(unit_Tasks_Tab)).click();
        Reporter.log("Click on unit task tab");

        pauseElementDisappear(driver.findElement(By.xpath(progressBar_Icon)));
        Reporter.log("pause until progress bar icon is disappear");
        MobileElement element01;

        try{
            element01 = (MobileElement) driver.findElement(By.xpath("(//XCUIElementTypeOther[contains(@name,'DD Unit by Unit Test - 1')])[17]"));

        }catch (Exception e){
            try{
                element01 = (MobileElement) driver.findElement(By.xpath("(//XCUIElementTypeOther[contains(@name,'Due Diligenca Unit by Unit - 1')])[17]"));

            }catch (Exception e1){
                element01 = (MobileElement) driver.findElement(By.xpath("(//XCUIElementTypeOther[contains(@name,'Test from - 1')])[17]"));
            }
        }
        Reporter.log("Get first unit task element");
        int startX = element01.getLocation().getX() + (element01.getSize().getWidth());
        int startY = element01.getLocation().getY() + (element01.getSize().getHeight() / 2);

        int endX = element01.getLocation().getX() + (element01.getSize().getWidth() / 10);
        int endY = element01.getLocation().getY() + (element01.getSize().getHeight() / 2);


        String UnitTaskName=element01.getText();
        Reporter.log("Get Unit Task Name: "+UnitTaskName);

        new TouchAction(driver)
                .press(point(startX,startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();

        Reporter.log("Swipe left first unit task element");
        driver.findElement(By.xpath("(//XCUIElementTypeOther[contains(@name,'DD Unit by Unit Test - 1')])[17]/following::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1] | (//XCUIElementTypeOther[contains(@name,'Due Diligence Unit by Unit - 1')])[17]/following::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1] | (//XCUIElementTypeOther[contains(@name,'Test from - 1')])[17]/following::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]")).click();

        pause(3);
        new TouchAction(driver).tap(PointOption.point(150,150)).perform();

    }




    public void pause(int sec) {

        try {
            Thread.sleep(1000*sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pauseElementDisappear(WebElement element) {
        wait = new WebDriverWait(driver, 300);
        wait.until(invisibilityOf(element));
    }
}
