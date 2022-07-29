package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Redbus {
	public static void main(String[] args) throws InterruptedException {
		//Initializing chromedriver using webdriver manager
		WebDriverManager.chromedriver().setup();
		//Creating object for chromedriver and maximizing the chrome
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implementing implict wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//Launching URL "https://www.redbus.in/"
		driver.get("https://www.redbus.in/");
		//Entering From -Madiwala Bangalore
		driver.findElement(By.id("src")).sendKeys("Madiwala Bangalore");
		driver.findElement(By.xpath("//li[contains(text(),'Madiwala')]")).click();
		//Entering To Koyambedu Chennai
		driver.findElement(By.id("dest")).sendKeys("Koyambedu Chennai");
		driver.findElement(By.xpath("//li[contains(text(),'Koyambedu, Chennai')]")).click();
		driver.findElement(By.id("onward_cal")).click();
		//Selecting the Date 30-Jul-2022
		driver.findElement(By.xpath("//td[contains(text(),'30')]")).click();
		//Clicking Search buses
		driver.findElement(By.id("search_btn")).click();
		//Clicking After 6pm under Departure time
		driver.findElement(By.xpath("//label[contains(text(),'After 6 pm')]")).click();
		//Clicking Sleeper under Bus types
		driver.findElement(By.xpath("//label[contains(text(),'SLEEPER')]")).click();
		//Selecting the Primo
		driver.findElement(By.xpath("//span[text()='Primo Bus']")).click();
		//Getting the number of buses found
		String busFoundStr = driver.findElement(By.xpath("//span[contains(text(),'found')]//span")).getText();
		System.out.println(busFoundStr);
		//Getting the Bus fare and sort them in ascending order
		driver.executeScript("window.scrollBy(0,2000)");
		List<WebElement> busFareList = driver.findElements(By.xpath("//div[@class='fare d-block']//span"));
		List<String> sortedBusFareList = new ArrayList<String>();
		for(int i = 0;i<busFareList.size();i++) {
			sortedBusFareList.add(busFareList.get(i).getText());
		}
		System.out.println("Sorted Bus Fare List: ");
		Collections.sort(sortedBusFareList);
		for(String sortedBusFare : sortedBusFareList) {
			System.out.println(sortedBusFare);
		}
		//Closing the application
		driver.quit();
	}
}
