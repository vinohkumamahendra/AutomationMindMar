package Sample;

import org.testng.annotations.BeforeTest;

public class Gmailtest extends Base {	

	@Override
	@BeforeTest
	public void openUrl() throws InterruptedException{	
		
		//System.out.println("Hello child class");
		
		driver.get("http://gmail.com");
		 Thread.sleep(1000);
		
	}
	
	public void login(){
		
	//	pause(10000);
	}
	
	public void compose(){
		
	}

}
