						package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public WebElement waitForVisibility(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForClickabilty(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void sendKeysWithWait(WebElement element, String text) {
		WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
		visibleElement.sendKeys(text);
	}
	

}
