package first;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Map {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
        // find the distance between Tel aviv and 4 cities in Europe
		// type the city with the shortest distance
		// tried using css as much as possible
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/Selenium/Drivers/chrome79/chromedriver.exe");
	    WebDriver driver=new ChromeDriver();
	    driver.get("https://www.mapdevelopers.com/distance_from_to.php");
	    driver.manage().window().maximize();
	    WebDriverWait waitVar=new WebDriverWait(driver,10);
	    
	    java.util.List<String>cities=Arrays.asList("Istanbul","Paris","London","Rome");
	    WebElement from=driver.findElement(By.cssSelector("[id='fromInput'][type='text']"));
	    from.sendKeys("Tel Aviv");
	    String[] str=new String[10];
	    int min=32000;
	    String city="";
	    WebElement to=driver.findElement(By.cssSelector("[id='toInput'][type='text']"));
	    WebElement calcDis=driver.findElement(By.partialLinkText("Calculate Distance"));//the calc btn
	    WebElement dis=driver.findElement(By.cssSelector("[id='status']"));
	    try {
	    for(int i=0;i<cities.size();i++) {
	    	to.sendKeys(cities.get(i));
	    	calcDis.click();
	    	calcDis.clear();
	    	Thread.sleep(3000);
		    dis=driver.findElement(By.cssSelector("[id='status']"));
            str=dis.getText().split(" ");
            to.clear();
	    	Thread.sleep(3000);
            if(min>(Integer.parseInt(str[7]))) {
            min=(Integer.parseInt(str[7]));
            city=cities.get(i);
            Thread.sleep(3000);
            }
	    }
	    }catch(Exception e) {e.printStackTrace();}
	    
    	Thread.sleep(3000);	    
	    System.out.println(String.format("shortest distance from "
	    		+ "Tel aviv is to the city of %s"
	    		+ " with the distance of %s KM ",city,min));
	    
	    WebElement addMap=driver.findElement(By.cssSelector("[id='m5'][onmouseover='mopen('m5')'][onmouseout='mclosetime()']"));
	    //addMap.isSelected();
	    waitVar.wait(3000);
	    //addMap.isDisplayed();
	    WebElement wh=driver.findElement(By.linkText("Where am I"));
	    waitVar.until(ExpectedConditions.visibilityOf(wh));
	    wh.isDisplayed();
	    wh.click();
	    WebElement myLocation=driver.findElement(By.cssSelector("[class='btn btn-default xs-btn'][onclick='getUserExactLocation();scrollToMap();']"));
	    myLocation.click();
	    waitVar.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[id='display_country']")));
	    WebElement isIsrael=driver.findElement(By.cssSelector("[id='display_country']"));
	    if (isIsrael.getText().equals("Israel")) {System.out.println("your location is currect");}
	    driver.close();
	}

}
