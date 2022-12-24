package KDD.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReusableFunction {

	WebDriver driver;
	public String[][] fetchDataFromExcel(){
		
		Workbook wb=null;
		String[][] data = null;
		try{
			String path = Fetchprop("KEYWORD_PATH");
			File excel=new File(path);
			FileInputStream file=new FileInputStream(excel);
			System.out.println(path);
			
			String extn = path.substring(path.indexOf(".") +1);
			System.out.println(extn);
			
			if(extn.equals("xlsx")){
				wb = new XSSFWorkbook(file);
			}else {
				wb = new HSSFWorkbook(file);
			}
			
			Sheet sheet = wb.getSheet("Sheet1");
			int rownum = sheet.getLastRowNum();
			System.out.println("Rows: " + rownum);
			
			int column = sheet.getRow(0).getLastCellNum();
			
			data = new String[rownum][column];
			
			for(int i=0; i< rownum; i++){
				Row row = sheet.getRow(i);
				for(int j=0; j< column; j++){
					Cell cell = row.getCell(j);
					String value = cell.toString();
					data[i][j]=value;
					}
			}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}finally {
				try{
					wb.close();
				}catch (IOException e){
					e.printStackTrace();
				}
				
			}
		return data;	
	}
	
	public String Fetchprop(String text){
		Properties prop=new Properties(); 
		FileInputStream input;
		try{
			input = new FileInputStream(System.getProperty("user.dir") + "\\KeywordDrivenFramework\\objects.properties");
			prop.load(input);
		}
		catch(Exception ex){
			
		}
		return prop.getProperty(text);
		
	}
	
	public void launchAppl(){
	System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
    driver=new ChromeDriver();
	driver.get(Fetchprop("URL"));

	}
	
	public void fillText(String locatorBy, String LocatorValue, String text){
		
	switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(LocatorValue)).sendKeys(text);
			
		case "name":
			driver.findElement(By.name(LocatorValue)).sendKeys(text);
		}
		
	}
	
}
