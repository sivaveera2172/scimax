package pageObjects;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CaseFormDefinitionPage extends BasePage {

	public CaseFormDefinitionPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//a[normalize-space()='Auto Text Setup']")
	WebElement lckAutoTextSetUp;
	@FindBy(xpath = "//a[@class='d-btn showpage pull-right'][normalize-space()='Add New']")
	WebElement lckAddNew;
	@FindBy(xpath = "//textarea[@id='txtDescription']")
	WebElement txtTextArea;
	@FindBy(xpath = "//label[normalize-space()='MI Component']")
	WebElement selctMIComp;
	@FindBy(xpath = "//label[normalize-space()='Other Component']")
	WebElement selctOtherComp;
	@FindBy(xpath = "//a[@onclick='SaveClick();']")
	WebElement lckSave;
	@FindBy(xpath = "(//div[@class='man-infosuccess'])[1]")
	WebElement successMsg;
	
	public void clickAutoTextSetup() {
		lckAutoTextSetUp.click();
	}
	public void clickAddNew() {
		lckAddNew.click();
	}
	public void setTextArea(String text) {
		txtTextArea.sendKeys(text);
	}
	public void SelectMiComponent() {
		selctMIComp.click();
	}
	public void SelectOtherComponent() {
		selctOtherComp.click();
	}
	public void clickSaveClose() {
		lckSave.click();
	}
	public String successMessage() {
		try {
			return (successMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
