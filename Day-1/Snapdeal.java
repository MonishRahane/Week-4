package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {
	public static void main(String[] args) throws InterruptedException, IOException {
		//Initializing chromedriver using webdriver manager
		WebDriverManager.chromedriver().setup();
		//Creating object for chromedriver and maximizing the chrome
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implementing implict wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//Launching URL "https://www.snapdeal.com/"
		driver.get("https://www.snapdeal.com/");
		//Searching on  Training Shoes
		Thread.sleep(2000);
		WebElement elementSearch = driver.findElement(By.name("keyword"));
		elementSearch.sendKeys("Training Shoes",Keys.ENTER);
		//Getting the count of the Training Shoes
		Thread.sleep(8000);
		WebElement elementCount = driver.findElement(By.className("nnn"));
		String countOfShoes = elementCount.getText();
		System.out.println(countOfShoes);
		//Clicking on Sort by  and select Low to High
		Thread.sleep(2000);
		WebElement elementSort = driver.findElement(By.xpath("//div[@class='sorting-sec animBounce']//div"));
		elementSort.click();
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[3]")).click();
		//Check if the items displayed are sorted correctly
		Thread.sleep(2000);
		List<WebElement> sortedShoeList = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<String> sortedPriceList = new ArrayList<String>();
		for(int i =0; i< sortedShoeList.size();i++ ) {
			String text = sortedShoeList.get(i).getText();
			System.out.println(text);
			sortedPriceList.add(text);
			String priceString = text.replaceAll("\\D", "");
			int priceInt = Integer.parseInt(priceString);
			System.out.println(priceInt);
		}
		//Entering the price range (900-1500)
		Thread.sleep(2000);
		WebElement elementFromVal = driver.findElement(By.name("fromVal"));
		elementFromVal.clear();
		elementFromVal.sendKeys("900");
		Thread.sleep(2000);
		WebElement elementToVal = driver.findElement(By.name("toVal"));
		elementToVal.clear();
		elementToVal.sendKeys("1500");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn')]")).click();
		//Filtering with color Blue 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@data-filtername='Color_s']")).click();
		//Verifying the Blue check box is enabled
		Thread.sleep(2000);
		WebElement blueCheckbox = driver.findElement(By.xpath("//label[@for='Color_s-Blue']"));
		blueCheckbox.click();
		System.out.println("Is Blue Checkbox Enabled: " + blueCheckbox.isEnabled());
		//Clicking on first resulting Training shoes
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@class='dp-widget-link noUdLine hashAdded']")).click();
		//Switching to new window
		Set<String> openedWindowsSet = driver.getWindowHandles();
		List<String> openedWindowsList = new ArrayList<String>(openedWindowsSet);
		driver.switchTo().window(openedWindowsList.get(1));
		//Printing the cost and the discount percentage
		Thread.sleep(8000);
		WebElement elementCost = driver.findElement(By.xpath("//span[@class='payBlkBig']"));
		String cost = elementCost.getText();
		System.out.println("Cost: " + cost);
		Thread.sleep(5000);
		WebElement elementDiscount = driver.findElement(By.xpath("//span[@class='pdpDiscount ']/span"));
		String discountPercentage = elementDiscount.getText();
		System.out.println("Discount Percentage: "+discountPercentage);
		//Taking the snapshot of the shoes
		Thread.sleep(5000);
		File imgsource = driver.getScreenshotAs(OutputType.FILE);
		File destSource = new File("src/test/resources/IMG01.png");
		FileUtils.copyFile(imgsource, destSource);
		driver.quit();
	}
}
