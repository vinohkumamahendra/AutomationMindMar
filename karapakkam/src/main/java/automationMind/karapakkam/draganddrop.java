package automationMind.karapakkam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class draganddrop {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "browser/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.seleniumeasy.com/test/drag-and-drop-demo.html");
		
		Thread.sleep(4000);
		Actions builder = new Actions(driver);
		WebElement From = driver.findElement(By.xpath("//span[contains(.,'Draggable 1')]"));
		WebElement To = driver.findElement(By.xpath("//div[@id='mydropzone']"));
	      Action dragAndDrop = builder.clickAndHold(From)
	    		  .moveToElement(To)
	    		  .release(To)
		   .build();

		  dragAndDrop.perform();
		  Thread.sleep(4000);
		
	}

}
