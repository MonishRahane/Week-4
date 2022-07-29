package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	public static void main(String[] args) throws InterruptedException {
		//Initializing chromedriver using webdriver manager
		WebDriverManager.chromedriver().setup();
		//Creating object for chromedriver and maximizing the chrome
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implementing implict wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//Launching URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		//Entering UserName and Password Using Id Locator
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys("Demosalesmanager");
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys("crmsfa");
		//Clicking on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		//Clicking on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		//Clicking on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		//Clicking on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//ul[@class='shortcuts']/li[4]")).click();
		//Clicking on Widget of From Contact
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
		String mainWindow = driver.getWindowHandle();
		Set<String> openedWindowsSet1 = driver.getWindowHandles();
		List<String> openedWindowsList1 = new ArrayList<String>(openedWindowsSet1);
		driver.switchTo().window(openedWindowsList1.get(1));
		//Clicking on First Resulting Contact
		driver.findElement(By.xpath("//a[@class='linktext'][1]")).click();
		driver.switchTo().window(mainWindow);
		//Clicking on Widget of To Contact
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> openedWindowsSet2 = driver.getWindowHandles();
		List<String> openedWindowsList2 = new ArrayList<String>(openedWindowsSet2);
		driver.switchTo().window(openedWindowsList2.get(1));
		//Clicking on Second Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[2]")).click();
		driver.switchTo().window(mainWindow);
		//Clicking on Merge button using Xpath Locator
		driver.findElement(By.className("buttonDangerous")).click();
		//Accepting the Alert
		driver.switchTo().alert().accept();
		//Verifying the title of the page
		String pageTitle = driver.getTitle();
		
		if(pageTitle.equals("View Contact | opentaps CRM")) {
			System.out.println("You are in correct page");
		}
		else {
			System.out.println("You are in wrong page");
		}
		
	}
}
