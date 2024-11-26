package testcases;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MycasesPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class TC_MC_001 extends BaseClass{
	
	ConfigReader config = new ConfigReader();
	
	
	@Test
	public void TC_MC_001testCase() {
		logger.info("**** Starting Test Case TC_MC_001 ****");
		try {
			String username = config.getProperty("username");
			String password = config.getProperty("password");
			String description = config.getProperty("description");
			String caseId = config.getProperty("caseId");
			
			LoginPage login = new LoginPage(driver);
			logger.info("*** Entering User Name and Password ***");
			login.setUserName(username);
			login.clickNext();
			login.setPassword(password);
			logger.info("*** Clicking Login Button ***");
			login.clickLogin();
			login.clckSessionYes();
			logger.info("*** Successful login and clicking on Case Management and clicking MyCases ***");
			HomePage home = new HomePage(driver);
			home.clickCaseManage();
			home.clickMycase();
			
			MycasesPage mycasesPage = new MycasesPage(driver);
			String msg = mycasesPage.CasePageMessage();
			Assert.assertEquals(msg, "My Casess", "Case Page Message Did Not Match");
			logger.info("*** Successfully verified MyCases Page ***");
			logger.info("*** Clicking on search Criteria Icon ");
			mycasesPage.clickSearchCriteriaIcon();
			mycasesPage.setCaseID(caseId);
			mycasesPage.clickSearch();
			
			
			boolean isSearchCaseIdDisplayed = mycasesPage.isOnlySearchedCaseDisplayed(caseId);
			assertTrue(isSearchCaseIdDisplayed, "Searched Case Id not Appeared");
			
			
//			String caseconfirmMsg = mycasesPage.caseConfirm();
//			Thread.sleep(Duration.ofSeconds(10));
//			System.out.println(caseconfirmMsg);
//			Assert.assertTrue(caseconfirmMsg.contains(caseId));
			
			
			
			
			
//			home.clickAdminConsoleIcon();
//			home.clickAdminConsole();
			
//			AdminPage admin = new AdminPage(driver);
//			admin.clickCaseFormDefinition();
			
			/*CaseFormDefinitionPage caseFormDefinitionPage = new CaseFormDefinitionPage(driver);
			caseFormDefinitionPage.clickAutoTextSetup();
			caseFormDefinitionPage.clickAddNew();
			caseFormDefinitionPage.setTextArea(description);
			caseFormDefinitionPage.SelectMiComponent();
			caseFormDefinitionPage.SelectOtherComponent();
			caseFormDefinitionPage.clickSaveClose();
			String Message = caseFormDefinitionPage.successMessage();
			Assert.assertEquals(Message, "New Auto Text added successfully ");*/
			
			
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}	
	}

}
