package resume;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class UploadResume 
{
	public WebDriver driver;
	
    public void resumeUpload() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
    {
    	System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
    	driver = new ChromeDriver();
    	
    	//opening the naukri site
    	driver.get("https://www.naukri.com/");
    	
    	//handling child windows
    	 
    	 String beforeWH = driver.getWindowHandle();
    	 

    	 Set<String> allWH = driver.getWindowHandles();
    	 allWH.remove(beforeWH);
    	 for(String wh:allWH)
    	 {
    		 
    		 driver.switchTo().window(wh);
    		 String title=driver.getTitle();
    		 System.out.println(title);
    		 driver.close();
    	 }
    	 
    	 driver.switchTo().window(beforeWH);
    	 
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	//Login to naukri account
    	driver.findElement(By.id("login_Layer")).click();
    	
    	
    	driver.findElement(By.id("eLogin")).sendKeys("geethadesai1606@gmail.com");
    	
    	driver.findElement(By.id("pLogin")).sendKeys("lookingforjob16");
    	driver.findElement(By.xpath("//button[@value='Login']")).click();
    	
    	
    	//cliking on My Naukri
        driver.findElement(By.xpath("//div[.='My Naukri']")).click();
        //String parent = driver.getWindowHandle();
        //clicking on view profile button
        driver.findElement(By.xpath("//button[@class='w205']")).click();
        
        //Script to scroll down
        
        //script to upload new resume
        driver.findElement(By.id("uploadLink")).click();
        driver.findElement(By.id("attachCV")).sendKeys("C:\\Users\\Geetu\\Desktop\\Geetha_Desai_Automation.docx");
        
        TakesScreenshot t=(TakesScreenshot) driver;
        Thread.sleep(5000);
        File srcFile=t.getScreenshotAs(OutputType.FILE);
        File destFile=new File("C:/Users/Geetu/Desktop/NaukriScreenshot/FileUpload.png");
        FileUtils.copyFile(srcFile, destFile);
        
        driver.findElement(By.xpath("//button[@type='button']")).click();
        
        //logout
        Actions action = new Actions(driver);
        WebElement logout = driver.findElement(By.xpath("//a[@class='logout']"));
        action.moveToElement(logout).click();
       
        //Closing the browser
        driver.quit();
    }
       
	public static void main(String[] args) throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
	{
		UploadResume r= new UploadResume();
		r.resumeUpload();
		
	}

}
