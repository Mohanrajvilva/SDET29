package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Assignment1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver;
		Properties p=new Properties();
		FileInputStream fs=new FileInputStream("./data/Commondata.properties");
		p.load(fs);
		
		String browser = p.getProperty("browser");
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String password =p.getProperty("password");
		String accountname= p.getProperty("accountname");
		String email = p.getProperty("email");
		String phone = p.getProperty("phone");
		String website = p.getProperty("website");
		String billingaddress = p.getProperty("billingaddress");
		String employees = p.getProperty("employees");
		
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(accountname);
		driver.findElement(By.name("website")).sendKeys(website);
		driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.id("email1")).sendKeys(email);
		driver.findElement(By.name("bill_street")).sendKeys(billingaddress);
		driver.findElement(By.name("button")).click();
		
		String organizationinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(organizationinfo.contains(accountname))
		{
			System.out.println("Organization created sucessfully");
		}
		else
		{
			System.out.println("test failed");
		}
		Actions a=new Actions(driver);
		
	}

}
