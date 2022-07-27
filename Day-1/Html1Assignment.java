package week4.day1.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Html1Assignment {
	public static void main(String[] args) {
		//Initializing chromedriver using webdriver manager
		WebDriverManager.chromedriver().setup();
		//Creating object for chromedriver and maximizing the chrome
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implementing implict wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//Launching URL "https://html.com/tags/table/"
		driver.get("https://html.com/tags/table/");
		//Getting the count of number of rows 
		List<WebElement> rowsList = driver.findElements(By.tagName("tr"));
		System.out.println("Number of Rows: " + rowsList.size());
		//Getting the count of number of columns
		List<WebElement> columnsList = driver.findElements(By.tagName("td"));
		System.out.println("Number of columns: " + columnsList.size());
		 
		driver.close();
	}
}
