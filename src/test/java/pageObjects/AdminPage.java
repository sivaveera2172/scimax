package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage{

	public AdminPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//a[normalize-space()='Case Form Definition']")
	WebElement lckCaseFprmDef;
	
	public void clickCaseFormDefinition() {
		lckCaseFprmDef.click();
	}
	

}
