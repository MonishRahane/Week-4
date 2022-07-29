package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingWindows {
	public static void main(String[] args) throws InterruptedException {
		//Initializing chromedriver using webdriver manager
		WebDriverManager.chromedriver().setup();
		//Creating object for chromedriver and maximizing the chrome
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implementing implict wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//Launching URL "http://www.leafground.com/pages/Window.html"
		driver.get("http://www.leafground.com/pages/Window.html");
		//Clicking the home button
		driver.findElement(By.id("home")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> openedWindowsSet1 = driver.getWindowHandles();
		//Printing the number of windows opened
		System.out.println("number of opened windows: " +  openedWindowsSet1.size());
		List<String> openedWindowsList = new ArrayList<String>(openedWindowsSet1);
		driver.switchTo().window(openedWindowsList.get(1));
		driver.close();
		//Switching to parent window
		driver.switchTo().window(parentWindow);
		//Clicking the multiple window button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		String parentWindow2 = driver.getWindowHandle();
		Set<String> openedWindowsSet2 = driver.getWindowHandles();
		List<String> openedWindowsList2 = new ArrayList<String>(openedWindowsSet2);
		int multipleWindowsOpenedSize = openedWindowsList2.size()-1;
		//Printing the number of multiple window opened
		System.out.println("Number of windows multiple opened: " + multipleWindowsOpenedSize);
		for(int i = 1;i<openedWindowsList2.size();i++) {
			driver.switchTo().window(openedWindowsList2.get(i));
			driver.close();
		}
		//Switching to parent window
		driver.switchTo().window(parentWindow2);
		//Clicking the wait for 5 seconds button
		driver.findElement(By.id("color")).click();
		Thread.sleep(5000);
	}
}
