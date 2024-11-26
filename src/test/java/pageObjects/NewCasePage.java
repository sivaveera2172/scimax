package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class NewCasePage extends BasePage{

	public NewCasePage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//input[@id='txt_CRI_FN']")
	WebElement txtFname;
	@FindBy(xpath = "//*[@id=\"txt_CRI_LN\"]")
	WebElement txtLname;
	@FindBy(xpath = "//*[@id=\"divRISec\"]/div/div[4]/div[1]/div/input")
	WebElement txtcountry;
	@FindBy(xpath = "//input[@data-ng-model=\"CurrentRep.COUNTRYID_NAME\"]")
	WebElement setcountry;
	@FindBy(xpath = "//select[@id='ddl_CRI_RT']")
	WebElement reqType;
	@FindBy(xpath = "//img[@spellcheck='true']")
	WebElement tradeName;
	@FindBy(xpath = "//span[normalize-space()='Aerosel (AF, 12)']")
	WebElement slctAerosel;
	@FindBy(xpath = "//a[@id='btnSelect']")
	WebElement saveClose;
	@FindBy(xpath = "//img[@src='images/editcaseicons/save.png']")
	WebElement lckSaveCase;
	@FindBy(xpath = "//div[@id='confirmPopUp']//div[@class='coformationlost']")
	WebElement cnfrmWindow;
	@FindBy(xpath = "//a[@id='confirmYes']")
	WebElement cnfrmYes;
	@FindBy(xpath = "//div[@class='edicasepageheading ng-binding ng-scope']")
	WebElement casetxt;
	
	
	public void setFname(String fname) {
		wait.until(ExpectedConditions.visibilityOf(txtFname)).sendKeys(fname);
	}
	public void setLname(String lname) {
		wait.until(ExpectedConditions.visibilityOf(txtLname)).sendKeys(lname);
	}

	public void setCountry(String searchTerm) {
		setcountry.sendKeys(searchTerm);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ui-id-13")));
		
		List<WebElement> suggestions =driver.findElements(By.cssSelector("#ui-id-13"));
		for(WebElement suggestion : suggestions) {
			System.out.println(suggestion.getText());
			if(suggestion.getText().equalsIgnoreCase("AUSTRALIA")) {
				suggestion.click();
			}
		}
	}
	public void requestorType() {
		Select select = new Select(reqType);
		select.selectByVisibleText("Health Care Professional");
	}
	public void clickTradename() {
		tradeName.click();
	}
	public void setAeroselAsTradeName() {
		slctAerosel.click();
		saveClose.click();
	}
	public void clickSaveCase() {
		lckSaveCase.click();
	}
	public void caseToYourSelfYes() {
		boolean cwindow = cnfrmWindow.isDisplayed();
		if(cwindow = true) {
			cnfrmYes.click();
		}
	}
	public String getCaseId() {
		String caseText = casetxt.getText();
		return extractCaseId(caseText);
	}
	// Method to extract case Id 
	public String extractCaseId(String text) {
		return text.substring(7,23);
	}
	
	
	
	
	

}
