package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pageObjects.LoginPage;
import utilities.ConfigReader;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;

	ConfigReader config = new ConfigReader();

	@BeforeClass
	public void setup() {
		logger = LogManager.getLogger(this.getClass());

		String url = config.getProperty("url");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().window().maximize();

	}

	public void login() {

		String username = config.getProperty("username");
		String password = config.getProperty("password");
		LoginPage login = new LoginPage(driver);
		login.setUserName(username);
		login.clickNext();
		login.setPassword(password);
		login.clickLogin();
		login.clckSessionYes();
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	public String captureScreen(String tname) {
		if(driver == null) {
			System.err.println("Driver is not initialized");
			return null;
		}
	    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    
	    if(driver instanceof TakesScreenshot) {
	    	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		    
		    String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
		    File targFile = new File(targetFilepath);
		    
		    File screenshotsDir = new File(System.getProperty("user.dir") + File.separator + "screenshots");
	        if (!screenshotsDir.exists()) {
	            screenshotsDir.mkdirs(); // Create the directory if it doesn't exist
	        }
	        try {
	            // Copy the screenshot to the desired location
	            org.openqa.selenium.io.FileHandler.copy(sourceFile, targFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return targetFilepath;
	    }else {
	    	System.err.println("Driver does not support screenshot capture");
	        return null;
	    }
	   
	}

}
