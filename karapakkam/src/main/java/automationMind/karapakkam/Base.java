package automationMind.karapakkam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import com.google.common.io.FileBackedOutputStream;

public class Base {

	public static String IrctcuserName, IrctcPswd, IrctcStatus = "passed";
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
			ChromeOptions chromeOptions= new ChromeOptions();
			chromeOptions.setBinary("C:\\Users\\mahev001\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			driver = new ChromeDriver(chromeOptions);
			C:\Users\mahev001\AppData\Local\Google\Chrome\Application
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
	
	
	public void pause(int milliseconds){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readIrctc(){
		try {
			XSSFWorkbook workBook = new XSSFWorkbook("Data/Credential.xlsx");
			XSSFSheet sheet = workBook.getSheet("IRCTC");
			Map<String, Integer> myMap = new HashMap<String, Integer>();
			for(int i=0; i <= sheet.getLastRowNum();  i++){
				for(int j=0; j < sheet.getRow(i).getLastCellNum()   ; j++){
					System.out.println("Cell value (" + i + "," + j + ") : " +  sheet.getRow(i).getCell(j) );
					if(i==0){
						myMap.put(sheet.getRow(i).getCell(j).getStringCellValue(), j);
					}
					else{
						if (myMap.get("UserName") == j){
							IrctcuserName = sheet.getRow(i).getCell(j).getStringCellValue();
						}
						if (myMap.get("Password") == j){
							IrctcPswd = sheet.getRow(i).getCell(j).getStringCellValue();
						}
					}
				}
				
			}
			
			System.out.println("User Credential " + IrctcuserName + " / " + IrctcPswd);
			
			
		} catch (IOException e) {
			System.out.println("Unable to read the file & error thrown as "  + e.getMessage());
		}
	}
	
	public void WriteExcel(){
		try {
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet();
			sheet.createRow(0).createCell(0).setCellValue("UserName");
			sheet.getRow(0).createCell(1).setCellValue("Password");
			sheet.getRow(0).createCell(2).setCellValue("Status");
			sheet.createRow(1).createCell(0).setCellValue(IrctcuserName);
			sheet.getRow(1).createCell(1).setCellValue(IrctcPswd);
			sheet.getRow(1).createCell(2).setCellValue(IrctcStatus);
			makeRowBold(workBook,sheet.getRow(0));
			FileOutputStream fos  = new FileOutputStream(new File("Data/Result.xlsx"));
			workBook.write(fos);
			
			
		}catch (IOException e) {
			System.out.println("Unable to write the file & error thrown as "  + e.getMessage());
		}
	}

	private static void makeRowBold(Workbook wb, Row row){
		    CellStyle style = wb.createCellStyle();//Create style
		    Font font = wb.createFont();//Create font
		    font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
		    style.setFont(font);//set it to bold
		    for(int i = 0; i < row.getLastCellNum()  ; i++){//For each cell in the row 
		        row.getCell(i).setCellStyle(style);//Set the style
		    }
	}
	
	public void readNWriteExcel(){
		try {
			InputStream  inp = new FileInputStream("Data/Credential.xlsx");
			Workbook workBook = WorkbookFactory. create(inp);
			Sheet sheet = workBook.getSheet("IRCTC");
			Map<String, Integer> myMap = new HashMap<String, Integer>();
			for(int i=0; i <= sheet.getLastRowNum();  i++){
				for(int j=0; j < sheet.getRow(i).getLastCellNum()   ; j++){
					System.out.println("Cell value (" + i + "," + j + ") : " +  sheet.getRow(i).getCell(j) );
					if(i==0){
						myMap.put(sheet.getRow(i).getCell(j).getStringCellValue(), j);
					}
					else{
						if (myMap.get("UserName") == j){
							IrctcuserName = sheet.getRow(i).getCell(j).getStringCellValue();
						}
						if (myMap.get("Password") == j){
							IrctcPswd = sheet.getRow(i).getCell(j).getStringCellValue();
						}
					}
				}
				
			}
			
			System.out.println("User Credential " + IrctcuserName + " / " + IrctcPswd);
			sheet.getRow(0).createCell(2).setCellValue("Status");
			sheet.getRow(1).createCell(2).setCellValue(IrctcStatus);
			makeRowBold(workBook,sheet.getRow(0));
			FileOutputStream fos  = new FileOutputStream(new File("Data/Credential.xlsx"));
			workBook.write(fos);
			
			
		} catch (Exception e) {
			System.out.println("Unable to read & write  the file & error thrown as "  + e.getMessage());
		}
	}
	
	public void writePropertyFile(String userName, String password){
		OutputStream output;
		try {
			Properties prop = new Properties();
			prop.setProperty("UserName", userName);
			prop.setProperty("Password", password);
			output = new FileOutputStream("Data/config.properties");
			prop.store(output, "create new file");
			output.close();
		} catch (Exception e) {
			System.out.println("Unable to write the file & error thrown as "  + e.getMessage());
		} 
		
	}
	
	public void readPropertyFile(){
		try {
			InputStream  input = new FileInputStream("Data/config.properties");
			Properties prop = new Properties();
			prop.load(input);
			System.out.println("UserName : " + prop.getProperty("UserName"));
			System.out.println("Password : " + prop.getProperty("Password"));
			input.close();
		} catch (Exception e) {
			System.out.println("Unable to read the file & error thrown as "  + e.getMessage());
		} 
		
	}
	
	public void modifyPropertyFile(String Password){
		try {
			FileInputStream in = new FileInputStream("Data/config.properties");
			Properties prop = new Properties();
			prop.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream("Data/config.properties");
			prop.setProperty("Password",Password);
			prop.store(out, "update the password");
			out.close();
		} catch (Exception e) {
			System.out.println("Unable to read and write the file & error thrown as "  + e.getMessage());// TODO Auto-generated catch block
			
		}
	}
	
	
}
