package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"IN\"]/a")
	WebElement lckInbox;

	@FindBy(xpath = "//*[@id=\"RC\"]/a/span")
	WebElement lckRecentCases;

	@FindBy(xpath = "//*[@id=\"M_W\"]/a/span")
	WebElement lckCaseManag;
	@FindBy(xpath = "//*[@id=\"MC\"]/a/span")
	WebElement lckMyCase;
	@FindBy(xpath = "//*[@id=\"UC\"]/a/span")
	WebElement lckUnassignCases;
	@FindBy(xpath = "//*[@id=\"M_CQ\"]/a/span")
	WebElement lckCaseQuery;

	// Logout WebElements
	@FindBy(xpath = "//*[@id=\"liLogout\"]/ul/li/a/img")
	WebElement lcklogOutImg;
	@FindBy(xpath = "//*[@id=\"liLogout\"]/ul/li/ul/li[2]/a[1]")
	WebElement lckLogoutYes;

	@FindBy(xpath = "//*[@id=\"NC\"]")
	WebElement lckNewCase;

	// Confirmation auto save window
	@FindBy(xpath = "//div[@id='confirmPopUp']//div[@class='coformationlost']")
	WebElement tempData;
	@FindBy(xpath = "//a[@id='confirmNo']")
	WebElement autoNo;

	// Admin console Element
	@FindBy(xpath = "//img[@src='images/console-setup.png']")
	WebElement adminconsolIcon;
	@FindBy(xpath = "//a[normalize-space()='Admin Console']")
	WebElement adminconsole;
	@FindBy(xpath = "//div[@id='CaseSiteChangePopup']//div[@class='popup-inner']")
	WebElement caseSitePopup;
	@FindBy(xpath = "//a[@onclick='ValidateNewCaseSiteAndOpenCaseForm()']")
	WebElement caseSitePopupYES;

	public void clickInbox() {
		lckInbox.click();
	}

	public void clickRecentCases() {
		lckRecentCases.click();
	}

	public void clickCaseManage() {
		lckCaseManag.click();
	}

	public void clickUnassignCases() {
		lckUnassignCases.click();
	}

	public void clickLogoutImg() {
		lcklogOutImg.click();
	}

	public void clickLogoutYes() {
		lckLogoutYes.click();
	}

	public void clickNewcase() {
		lckNewCase.click();
	}

	public void autoSaveWindow() {
		try {
			boolean autoSavetempData = tempData.isDisplayed();
			if (autoSavetempData = true) {
				autoNo.click();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickAdminConsoleIcon() {
		adminconsolIcon.click();
	}

	public void clickAdminConsole() {
		adminconsole.click();
	}

	public void clickMycase() {
		lckMyCase.click();
	}
	public void caseSiteChangePopUpYES() {
		try {
			boolean casesite = caseSitePopup.isDisplayed();
			if(casesite = true) {
				caseSitePopupYES.click();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
