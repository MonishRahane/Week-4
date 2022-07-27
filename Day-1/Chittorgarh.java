package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittorgarh {
	public static void main(String[] args) throws InterruptedException {
		//Initializing chromedriver using webdriver manager
		WebDriverManager.chromedriver().setup();
		//Creating object for chromedriver and maximizing the chrome
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implementing implict wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//Launching URL "https://www.chittorgarh.com/"
		driver.get("https://www.chittorgarh.com/");
		//Clicking on stock market
		driver.findElement(By.id("navbtn_stockmarket")).click();
		//Clicking on NSE bulk Deals
		driver.findElement(By.linkText("NSE Bulk Deals")).click();
		//Fetching all the Security names
		List<String> securityNamesList = new ArrayList<String>();
		WebElement elementTable = driver.findElement(By.xpath("//table[@class='table table-bordered table-condensed table-striped']"));
		List<WebElement> elementRows = elementTable.findElements(By.tagName("tr"));
		for(int i=1; i< elementRows.size();i++) {
			WebElement eachRow = elementRows.get(i);
			List<WebElement> cols = eachRow.findElements(By.tagName("td"));
			securityNamesList.add(cols.get(2).getText());
		}
		Set <String> securityNamesSet = new TreeSet<String>(securityNamesList);
		//Ensuring whether there are duplicate Security names
		if(securityNamesList.size() != securityNamesSet.size()) {
			System.out.println("Yes the security names contains duplicates");
		}
		
	}
}
