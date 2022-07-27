package week4.day1.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Html2Assignment {
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
		//Printing the respective values based on given Library
		WebElement elementTable = driver.findElement(By.xpath("//table[1]"));
		List<WebElement> elementRows = elementTable.findElements(By.tagName("tr"));
		System.out.println(elementRows.get(0).getText());
		System.out.println("****************************");
		System.out.println(elementRows.get(1).getText());
	}
}
