package Sample;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FrameTesting extends Base {	

	
	@Test(groups = {"regression","sustains"}, testName = "iframe")
	public void checkIframe() throws InterruptedException, IOException{
//		driver.switchTo().frame(0);
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//input[@id='sex-0']")).click();
//		driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("23/3/2017");
//		driver.findElement(By.xpath("//input[@id='profession-0']")).click();
//		System.out.println(driver.getCurrentUrl());
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	FileUtils.copyFile(src, new File("Images/proof.jpeg"));
		
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
	}
	
}
