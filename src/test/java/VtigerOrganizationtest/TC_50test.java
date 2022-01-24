package VtigerOrganizationtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class TC_50test {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver;
		Properties p=new Properties();
		FileInputStream fs=new FileInputStream("./data/Commondata.properties");
		p.load(fs);
		
		String browser = p.getProperty("browser");
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String password =p.getProperty("password");
		
		
		FileInputStream fs1=new FileInputStream("./data/data.xlsx");
		Workbook wb = WorkbookFactory.create(fs1);
		String accountname = wb.getSheet("Sheet1").getRow(9).getCell(4).getStringCellValue();
		
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else {
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(4000,TimeUnit.SECONDS);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(accountname);
		
		
		driver.findElement(By.name("button")).click();
	
		Actions a=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(ele).perform();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Sign Out")).click();

		String organizationinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(organizationinfo.contains(accountname))
		{
			System.out.println("Test case passed");
		}
		else
		{
			System.out.println("test case failed");
		}
		
		
		driver.close();
	}

}
