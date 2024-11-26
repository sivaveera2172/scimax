package testcases;

import pageObjects.HomePage;
import pageObjects.MycasesPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class TC_MC_006 extends BaseClass {
	
	ConfigReader config = new ConfigReader();
	
	public void readOnlyAccessCase() {
		String siteName = config.getProperty("siteName");
		String orgname = config.getProperty("orgname");
		String siteName004 = config.getProperty("siteName004");
		login();
		HomePage home = new HomePage(driver);
		home.clickCaseManage();
		home.clickMycase();

		MycasesPage mycasesPage = new MycasesPage(driver);
		mycasesPage.clickSearchCriteriaIcon();
		mycasesPage.selectOrganization(orgname);
		mycasesPage.selectSite(siteName004);
		mycasesPage.clickSearch();
		
	}

}
