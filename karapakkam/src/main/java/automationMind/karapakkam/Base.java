package automationMind.karapakkam;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Base {

	public WebDriver driver =  null ;
	@BeforeClass
	@Parameters({"browser"})
	public void openBrowser(String browser){
		System.out.println("I'm in Before suite");
		if(browser.equals("mozilla")){
			System.setProperty("webdriver.gecko.driver", 
					 "browser/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "browser/chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("I'm in Before suite");
		}
		else if(browser.equals("explorer")){
			System.setProperty("webdriver.ie.driver", "browser/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else{
			System.setProperty("webdriver.gecko.driver", 
					 "browser/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		 
		driver.manage().window().maximize();
	}
	
	@AfterSuite
	public void closeBrowser(){
		System.out.println("I'm in After suite");
		 driver.close();
	}
	
	
	@AfterClass
	public void afterC(){
		System.out.println("I'm in After Class");
	}
	@AfterMethod
	public void AfterTest(){
		System.out.println("I'm in After Test");
	}
	
	@BeforeMethod
	public void openUrl() throws InterruptedException{	
		
		System.out.println("Hello base class");
		 driver.get("http://toolsqa.com/iframe-practice-page");
		 Thread.sleep(1000);
		
	}
	
//	
//	public void pause(int milliseconds){
//		try {
//			Thread.sleep(milliseconds);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
