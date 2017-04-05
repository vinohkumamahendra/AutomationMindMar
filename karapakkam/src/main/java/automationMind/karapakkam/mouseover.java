package automationMind.karapakkam;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class mouseover {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "browser/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com");
		
		Thread.sleep(4000);
		WebElement electrons = driver.findElement(By.xpath("//span[contains(.,'Electronics')]"));

		Actions action = new Actions(driver);
		action.moveToElement(electrons).perform();
		
		Thread.sleep(4000);
		
		action.contextClick(electrons).
		sendKeys(Keys.ARROW_DOWN).
		sendKeys(Keys.ENTER).build().perform();
		
		Thread.sleep(4000);

		
		
		
		
	}
}
