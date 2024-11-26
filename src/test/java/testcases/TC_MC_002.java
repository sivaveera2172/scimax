package testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AdminPage;
import pageObjects.CaseFormDefinitionPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MycasesPage;
import pageObjects.NewCasePage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class TC_MC_002 extends BaseClass {
	
	ConfigReader config = new ConfigReader();
	
	@Test(enabled = true)
	public void TC_MC_002_testCase() {
		try {
			
			String lname = config.getProperty("lname");
			String country = config.getProperty("country");
			
		login();
			
			HomePage home = new HomePage(driver);
			
			home.clickNewcase();
			home.caseSiteChangePopUpYES();
			home.autoSaveWindow();
			
			NewCasePage newCase = new NewCasePage(driver);
			Thread.sleep(Duration.ofSeconds(10));
			newCase.setLname(lname);
//			newCase.selectOptionsFromDropdown(country);
			newCase.setCountry(country);
			newCase.requestorType();
			newCase.clickTradename();
			newCase.setAeroselAsTradeName();
			newCase.clickSaveCase();
			newCase.caseToYourSelfYes();
			System.out.println(newCase.getCaseId());
			
			
			home.clickCaseManage();
			home.clickMycase();
			
			String mycaseId = newCase.getCaseId();
			MycasesPage mycasesPage = new MycasesPage(driver);
			boolean isCaseIdPresent = mycasesPage.isCaseIdInTable(mycaseId);
			Assert.assertTrue(isCaseIdPresent, "Case Id Not Present in Table");
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	

}
















