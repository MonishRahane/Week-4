package week4.day1.assignments;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable {
	public static void main(String[] args) {
		//Initializing chromedriver using webdriver manager
		WebDriverManager.chromedriver().setup();
		//Creating object for chromedriver and maximizing the chrome
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implementing implict wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//Launching URL "http://www.leafground.com/pages/table.html "
		driver.get("http://www.leafground.com/pages/table.html ");
		//Fetched the count of number of rows
		List<WebElement> elementRows = driver.findElements(By.tagName("tr"));
		System.out.println("Table Row Size: " + elementRows.size());
		//Fetched the count of number of columns
		List<WebElement> elementColumns = driver.findElements(By.tagName("td"));
		System.out.println("Table Column size: " + elementColumns.size());
		//Fetching the progress value of 'Learn to interact with Elements'
		String progressValue = elementColumns.get(7).getText();
		System.out.println(progressValue);
		//Checking the vital task for the least completed progress
		driver.findElement(By.xpath("//tr[6]//td[3]//input[@name='vital']")).click();
		driver.close();
	}
}
