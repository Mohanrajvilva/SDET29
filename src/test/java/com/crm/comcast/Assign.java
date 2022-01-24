package com.crm.comcast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class Assign {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver;
		FileInputStream fs=new FileInputStream("./data/data.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		String browser = wb.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue();
		String url = wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
		String username = wb.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue();
		String password = wb.getSheet("Sheet1").getRow(1).getCell(3).getStringCellValue();
		String accountname = wb.getSheet("Sheet1").getRow(1).getCell(4).getStringCellValue();
		String website = wb.getSheet("Sheet1").getRow(1).getCell(5).getStringCellValue();
		//String employees = wb.getSheet("Sheet1").getRow(1).getCell(6).getStringCellValue();
		String email = wb.getSheet("Sheet1").getRow(1).getCell(7).getStringCellValue();
		//String phone = wb.getSheet("Sheet1").getRow(1).getCell(8).getStringCellValue();
		String billingaddress = wb.getSheet("Sheet1").getRow(1).getCell(9).getStringCellValue();
		
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
		driver.findElement(By.name("website")).sendKeys(website);
		//driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.id("email1")).sendKeys(email);
		driver.findElement(By.name("bill_street")).sendKeys(billingaddress);
		//driver.findElement(By.id("employees")).sendKeys(employees);
		driver.findElement(By.name("button")).click();
		
		Actions a=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]"));
		a.moveToElement(ele);
		driver.findElement(By.linkText("Sign out")).click();
		
		driver.close();
		
		
		
		
		
		
		
		
	}

}
