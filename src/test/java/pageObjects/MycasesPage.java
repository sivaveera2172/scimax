package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MycasesPage extends BasePage {

	public MycasesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='edicasepageheading re-batchheading']")
	WebElement casePagemsg;
	@FindBy(xpath = "//span[@id='pm']")
	WebElement lckSearchCriteria;
	@FindBy(xpath = "//input[@name='Case ID']")
	WebElement txtCaseID;
	@FindBy(xpath = "//a[normalize-space()='Search']")
	WebElement lckSearch;
	@FindBy(xpath = "//tr[@class='ng-scope active']")
	List<WebElement> tableRows;
	@FindBy(xpath = "//select[@id='ddlGOrganization']")
	WebElement selctOrg;
	@FindBy(xpath = "//select[@name='Prifix']")
	WebElement selctSite;

	@FindBy(xpath = "//a[@class='a-menu clsetting']//img[@alt='copy']")
	WebElement lckgear;
//	@FindBy(xpath = "//div[@class='pull-left details-box-show']//li//li")
//	List<WebElement> permissionList;
	@FindBy(xpath = "//ul[@id='CaseDetailsSetting']")
	WebElement getTxtMsg;
	@FindBy(xpath = "//*[@id=\"divMyCasesTbl\"]/table/tbody/tr[4]/td[1]")
	WebElement tblColum;
	@FindBy(xpath = "//*[@id=\"main\"]/div[21]/div/div[2]/div[2]/div[2]/div/div[1]")
	WebElement detailView;
	@FindBy(xpath = "//*[@id=\"divMyCasesTbl\"]/table/tbody/tr[2]/td[8]")
	WebElement hoverforAction;
	@FindBy(xpath ="//*[@id=\"divMyCasesTbl\"]/table/tbody/tr[2]/td[8]/div/a")
	WebElement lckActionIcon;

	public String CasePageMessage() {
		try {
			return (casePagemsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public void clickSearchCriteriaIcon() {
		lckSearchCriteria.click();
	}

	public void setCaseID(String caseId) {
		txtCaseID.sendKeys(caseId);
	}

	public void clickSearch() {
		lckSearch.click();
	}

	// Method to verify if a case Id is present in Table or not
	public boolean isCaseIdInTable(String caseId) {
		for (WebElement row : tableRows) {
			WebElement cellIdCell = row.findElement(By.xpath(".//td[column_index]"));
			if (caseId.equals(cellIdCell.getText())) {
				return true;
			}
		}
		return false;
	}

	public String getCaseIdFromTable(int columnindex) {
		int retryCount = 0;
		String caseId = "";
		while (retryCount < 3) {
			try {
				WebElement caseIdElement = driver.findElement(By.xpath("//table//tr[1]//td[" + columnindex + "]"));
				caseId = caseIdElement.getText();
				System.out.println(caseId);
			} catch (StaleElementReferenceException e) {
				System.out.println("Encountered StaleElementReferenceException. Retrying...");
				retryCount++;
			}
		}
		if (caseId.isEmpty()) {
			System.out.println("Unable to find Case ID after retrying.");
		}
		return caseId;
	}

	// Method to display only searched case ID
	public boolean isOnlySearchedCaseDisplayed(String caseId) {
		for (WebElement row : tableRows) {
			WebElement caseIdCell = row.findElement(By.xpath("//td[normalize-space()='JP-aza-2024-1286']"));
			if (!caseId.equals(caseIdCell.getText())) {
				return false;
			}
		}
		return true;
	}

	public void selectOrganization(String orgname) {
		Select select = new Select(selctOrg);
		select.selectByVisibleText(orgname);
	}

	public void selectSite(String siteName004) {
		Select select = new Select(selctSite);
		select.selectByVisibleText(siteName004);
	}

//	public boolean doCaseIdContainsSiteName(String siteName) {
//		for(WebElement row : tableRows) {
//			WebElement caseIdCell = row.findElement(By.xpath(".//td[column_index]"));
//			String caseId = caseIdCell.getText();
//			if(!caseId.contains(siteName)) {
//				return false;
//			}
//		}
//		return true;
//	}

	public void clickGearIcon() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", lckgear);
	}

	public String accessPermissionMsg() {
		try {
			return getTxtMsg.getText();

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// Method to verify cases related to Full Access site
	public boolean doCaseIdContainsSiteName(String siteName) {
		int retryCount = 0;
		boolean containsSiteName = false;

		while (retryCount < 3) {
			try {
				for (WebElement row : tableRows) {

					WebElement caseIdCell = row.findElement(
							By.xpath(".//td[normalize-space(text()) and contains(text(), '" + siteName  + "')]"));
					String caseId = caseIdCell.getText();
					if (!caseId.contains(siteName)) {
						return false;
					}
				}
				containsSiteName = true;
				break;

			} catch (StaleElementReferenceException e) {
				System.out.println("Encountered StaleElementReferenceException. Retrying...");
				retryCount++;
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}

		if (!containsSiteName && retryCount == 3) {
			System.out.println("Unable to verify case IDs after retries due to stale element issues.");
		}

		return containsSiteName;
	}
	// Method to verify cases related to Read Access site
	public boolean doCaseIdContainsReadSiteName(String readSiteName) {
		int retryCount = 0;
		boolean containsSiteName = false;

		while (retryCount < 3) {
			try {
				for (WebElement row : tableRows) {

					WebElement caseIdCell = row.findElement(
							By.xpath(".//td[normalize-space(text()) and contains(text(), '" + readSiteName  + "')]"));
					String caseId = caseIdCell.getText();
					if (!caseId.contains(readSiteName)) {
						return false;
					}
				}
				containsSiteName = true;
				break;

			} catch (StaleElementReferenceException e) {
				System.out.println("Encountered StaleElementReferenceException. Retrying...");
				retryCount++;
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}

		if (!containsSiteName && retryCount == 3) {
			System.out.println("Unable to verify case IDs after retries due to stale element issues.");
		}

		return containsSiteName;
	}
	public void clicktableColumn4() {
		tblColum.click();
	}
	public String detailViewHeadText() {
		try {
			return detailView.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	public String column4text() {
		try {
			return tblColum.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	public void hoverForActionAndClick() {
		Actions actions = new Actions(driver);
		actions.moveToElement(hoverforAction);
		actions.moveToElement(lckActionIcon);
		actions.click().build().perform();
	}
	

}
