package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {
	public static void main(String[] args) throws InterruptedException {
		//Initializing chromedriver using webdriver manager
		WebDriverManager.chromedriver().setup();
		//Creating object for chromedriver and maximizing the chrome
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implementing implict wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//Launching URL "https://www.nykaa.com/"
		driver.get("https://www.nykaa.com/");
		//Hovering Brands and Search L'Oreal Paris
		WebElement elementBrand = driver.findElement(By.xpath("//a[contains(text(),'brands')]"));
		Actions act = new Actions(driver);
		act.moveToElement(elementBrand).perform();
		//Clicking L'Oreal Paris
		driver.findElement(By.xpath("//img[contains(@src,'lorealparis')]")).click();
		//Checking the title contains L'Oreal Paris
		String pageTitle = driver.getTitle();
		if(pageTitle.contains("Paris")) {
			System.out.println("Page Contains Paris");
		}
		else {
			System.out.println("Page does not contains paris");
		}
		//Clicking sort By and select customer top rated
		driver.findElement(By.xpath("//span[contains(text(),'popularity')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'top rated')]")).click();
		//Clicking Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		//Clicking->Concern->Color Protection
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		//Checking whether the Filter is applied with Shampoo
		String filterApplied = driver.findElement(By.xpath("//span[text()='Shampoo']")).getText();
		if(filterApplied.contains("Shampoo")) {
			System.out.println("Shampoo filter applied");
		}
		else {
			System.out.println("Shampoo filter have not been applied");
		}
		//Clicking on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[contains(text(),'Colour Protect Shampoo')]")).click();
		//Switching to the new window and select size as 175ml
		String parentWindow = driver.getWindowHandle();
		Set<String> openedWindowsSet = driver.getWindowHandles();
		List<String> openedWindowList = new ArrayList<String>(openedWindowsSet);
		driver.switchTo().window(openedWindowList.get(1));
		driver.findElement(By.xpath("//option[text()='175ml']")).click();
		//Printing the MRP of the product
		String mrpStr = driver.findElement(By.xpath("//span[contains(text(),'MRP')]/following::span[1]")).getText();
		String ReplaceAll1 = mrpStr.replaceAll("\\D", "");
		int mrpInt = Integer.parseInt(ReplaceAll1);
		System.out.println("MRP: "+mrpInt);
		//Clicking on ADD to BAG
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		//Locating to Shopping Bag
		Thread.sleep(3000); 	
		driver.findElement(By.xpath("//div[@class='css-0 e1ewpqpu1']")).click();
		//Printing the Grand Total amount
		WebElement framebag = driver.findElement(By.xpath("(//iframe[contains(@src,'customIframeCart')])[1]"));
		driver.switchTo().frame(framebag);
		Thread.sleep(8000);
		String grandTotalStr1 = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		String replaceAll2 = grandTotalStr1.replaceAll("\\D","");
		int grandTotalInt1 = Integer.parseInt(replaceAll2);
		System.out.println("Grand Total: " + grandTotalInt1);
		//Clicking Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		//Clicking on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		//Checking if this grand total is the same in step 14
		Thread.sleep(3000);
		String grandToralstr2 = driver.findElement(By.xpath("//div[text()='Grand Total']/following::div")).getText();
		String replaceAll3 = grandToralstr2.replaceAll("\\D", "");
		int grandTotalInt2 = Integer.parseInt(replaceAll3);
		
		if(grandTotalInt1 != grandTotalInt2) {
			System.out.println("Grand total is not same");
		}
		else {
			System.out.println("Grand Total is same");
		}
		//Closing all windows
		driver.quit();
	}
}
