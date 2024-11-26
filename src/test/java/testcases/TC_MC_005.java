package testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MycasesPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class TC_MC_005 extends BaseClass {
	
	ConfigReader config = new ConfigReader();
	@Test
	public void TC_MC_005_testCase() {
		
		try {
			 
			String caseId = config.getProperty("caseId");
			String readSiteName = config.getProperty("readSiteName");
			String orgname = config.getProperty("orgname");
			String readOnlySite = config.getProperty("readOnlySite");
			String readOnlyMessage = config.getProperty("readOnlyMessage");
			
			login();
			
			HomePage home = new HomePage(driver);
			home.clickCaseManage();
			home.clickMycase();
			
			MycasesPage mycasesPage = new MycasesPage(driver);
			mycasesPage.clickSearchCriteriaIcon();
			mycasesPage.selectOrganization(orgname);
			mycasesPage.selectSite(readOnlySite);
			mycasesPage.clickSearch();
			Thread.sleep(Duration.ofSeconds(10));
			boolean caseContainSiteName = mycasesPage.doCaseIdContainsReadSiteName(readSiteName);
			Assert.assertTrue(caseContainSiteName, "Case Id Does Not contain Site Name");
			
//			mycasesPage.clickGearIcon();
//			int fullAccessPermissionCount = mycasesPage.getDisplayedPermissionCount();
//			Assert.assertEquals(fullAccessPermissionCount,5, "Full Access Should Have 5 Permissions");
			
			mycasesPage.clickGearIcon();
			String accessReadMsg = mycasesPage.accessPermissionMsg();
			System.out.println(accessReadMsg);
			Assert.assertTrue(accessReadMsg.length() == readOnlyMessage.length());
//			int readOnlyAccessPermissionCount = mycasesPage.getDisplayedPermissionCount();
//			System.out.println(readOnlyAccessPermissionCount);
//			Assert.assertEquals(readOnlyAccessPermissionCount,2, "Read only Access Should Have 2 Permissions");
			
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
