package com.crm.comcast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.jdbc.Driver;

public class Jdbc {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, SQLException {
		// TODO Auto-generated method stub
		WebDriver driver;
	
		FileInputStream fs=new FileInputStream("./data/jdbc.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		String browser = wb.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue();
		String url = wb.getSheet("Sheet1").getRow(1).getCell(5).getStringCellValue();
		String username = wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
		String password = wb.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue();
		String projectname = wb.getSheet("Sheet1").getRow(1).getCell(3).getStringCellValue();
		String projectmanager = wb.getSheet("Sheet1").getRow(1).getCell(4).getStringCellValue();
		
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
		
		driver.findElement(By.id("usernmae")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectname);
		driver.findElement(By.name("createdBy")).sendKeys(projectmanager);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.close();
		
		Driver drf=new Driver();
		DriverManager.registerDriver(drf);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root","root");
		Statement stmt = con.createStatement();
		ResultSet set = stmt.executeQuery("Select * from project;");
		
		while(set.next())
		{
		
			String apn = set.getString(4);
			if(apn.equals(projectname)) {
				System.out.println("Project added to database");
			}
			else
			{
				System.out.println("Projecct not added to database");
			}
		}
		con.close();
		
	}

}
