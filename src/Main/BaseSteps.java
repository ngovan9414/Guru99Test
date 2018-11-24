package Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseSteps {
	public static WebDriver driver;
	
	public static void typeText(String xpath, String type) {
		driver.findElement(By.xpath(xpath)).sendKeys(type);
	}
	
	public static void click(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public static String getText(String xpath) {
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	public static void assertTextEquals(String field, String xpath, String expected) {
		boolean flag = getText(xpath).equals(expected);
		if (!flag) {
			System.err.println(field + " Expected is " + expected + " but actual is " + getText(xpath));
		}
		else {
			System.out.println(field + " is " + getText(xpath));
		}
	}
	public static boolean isExisted(String xpath) {
		if(driver.findElement(By.xpath(xpath))!=null){
			return true;
		} else {
			return false;
		}
	}
	public static void checkExist(String xpath) {
		if (!isExisted(xpath)) {
			System.err.println("XPath: " + xpath +" does not exist ");
		} else {
			System.out.println("XPath: " + xpath +" exists");
		}

	}

}
