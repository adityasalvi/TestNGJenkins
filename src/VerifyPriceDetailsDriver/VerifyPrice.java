package VerifyPriceDetailsDriver;

import org.testng.annotations.Test;

import GenFunc.GenFunc;
import PageF.PageFac;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class VerifyPrice {
	
	public static WebDriver driver;	
	private static PageFac objFactory;
	private static WebDriverWait wait;
	private static Actions builder;
	private static String firefoxDriverPath = "./geckodriver.exe";
	private static String chromeDriverPath = "./chromedriver.exe";
	private long searchResultsCount = 0;
	private long searchPagePrice = 0;
	private long detailsPagePrice = 0;
	
  @Test(priority = 1,dataProvider = "SearchProvider")
  public void enterSearchDetails(String s) {
	  
	   wait.until(ExpectedConditions.visibilityOf(objFactory.searchBox));
		   	
	   GenFunc.enterText(objFactory.searchBox, s);
	   	
	   GenFunc.pressKeys(objFactory.searchBox, "ENTER");
	  
  }
  
  @Test(priority = 2)
  public void getSearchResultsCount() {
	  
	   wait.until(ExpectedConditions.visibilityOf(objFactory.searchResultCount));	
	   
	   searchResultsCount = Integer.parseInt(GenFunc.getTextFromElement(objFactory.searchResultCount).replaceAll("[A-Za-z ]", ""));
	   
	   assertTrue(searchResultsCount > 0); // Verify Search are greater than zero
	  
  }
  
  @Test(priority = 3)
  public void getPagePrice() {
	  
	   searchPagePrice = Long.parseLong(GenFunc.getTextFromElement(objFactory.searchResultPrice).replaceAll("[$, ]", ""));

	   builder
      .moveToElement(objFactory.secondLinkAddress).click().perform();
	  
  }
  
  @Test(priority = 4)
  public void verifyPriceDetails() {
	  
	   wait.until(ExpectedConditions.visibilityOf(objFactory.viewDetailsPrice));
	   
	   detailsPagePrice = Long.parseLong(GenFunc.getTextFromElement(objFactory.viewDetailsPrice).replaceAll("[$, ]", ""));
	   
	   assertEquals(searchPagePrice, detailsPagePrice); // Verify Price are same for Search and Details page
	  
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }


  @DataProvider(name="SearchProvider")
  public Object[][] dp(Method m) {
	  String methodName = m.getName();
	  switch(methodName){
	  	case "enterSearchDetails": 
	  		return new Object[][] {
                { "Morgantown, WV" }
            };
	  	default: return new Object[][] {
            { "Invalid Value" }
        };
	  }

  }
  
  @BeforeClass
  public void beforeClass() {
	  
	  	driver.get("http://www.realtor.com/"); 
	   	
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver,10);
		
		objFactory = new PageFac(driver); //Initializing Object Factory
		
		builder = new Actions(driver); //Initializing Actions driver
  }

  @AfterClass
  public void afterClass() {
	  
  }

  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", chromeDriverPath);
	   driver = new ChromeDriver();
  }

  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
