package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MycasesPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class TS_MC_043 extends BaseClass {
	ConfigReader config = new ConfigReader();
	@Test
	public void TS_MC_043_testCase(){
		try {
			
			login();
			HomePage home = new HomePage(driver);
			home.clickCaseManage();
			home.clickMycase();

			MycasesPage mycasesPage = new MycasesPage(driver);
			mycasesPage.clicktableColumn4();
			String columnText4 = mycasesPage.column4text();
			String detailViewHeadText = mycasesPage.detailViewHeadText();
			Assert.assertTrue(detailViewHeadText.contains(columnText4));
			System.out.println(columnText4);
			System.out.println(detailViewHeadText);
			
			mycasesPage.hoverForActionAndClick();
			System.out.println("HELLO");
			
		} catch (Exception e) {
		}
	}

}
