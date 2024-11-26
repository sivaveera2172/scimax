package testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MycasesPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class TC_MC_004 extends BaseClass {

	ConfigReader config = new ConfigReader();

	@Test
	public void TC_MC_004_testCase() {
		try {

			String siteName = config.getProperty("siteName");
			String orgname = config.getProperty("orgname");
			String siteName004 = config.getProperty("siteName004");
			String fullAccessMessage = config.getProperty("fullAccessMessage");

			login();

			HomePage home = new HomePage(driver);
			home.clickCaseManage();
			home.clickMycase();

			MycasesPage mycasesPage = new MycasesPage(driver);
			mycasesPage.clickSearchCriteriaIcon();
			mycasesPage.selectOrganization(orgname);
			mycasesPage.selectSite(siteName004);
			mycasesPage.clickSearch();
		
			boolean caseContainSiteName = mycasesPage.doCaseIdContainsSiteName(siteName);
			Assert.assertTrue(caseContainSiteName, "Case Id Does Not contain Site Name");
			Thread.sleep(Duration.ofSeconds(10));
			mycasesPage.clickGearIcon();
			String fullAccesMsg = mycasesPage.accessPermissionMsg();
			Assert.assertTrue(fullAccesMsg.length() == fullAccessMessage.length());
//			System.out.println(mycasesPage.getDisplayedPermissionCount());
//			int fullAccessPermissionCount = mycasesPage.getDisplayedPermissionCount();
//			Assert.assertEquals(fullAccessPermissionCount,5, "Full Access Should Have 5 Permissions");
			System.out.println(fullAccesMsg.length());
			System.out.println(fullAccessMessage.length());

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
