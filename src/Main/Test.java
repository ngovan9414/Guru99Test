package Main;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test extends BaseSteps{
	public static String customer = null;
	public static String account = null;
	public static String name =null;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		// Open app url
		driver.get("http://demo.guru99.com/v4/");
		login();
		// create Customer
		createCustomer();		
		//verify customer just created
		verifyCustomer();
		// create new account
		craeteNewAccount();
		// verify new account just created
		verifyNewAccount();
		// create amount deposit
		createAmountDeposit();
		// Verify deposit function
		verifyAmountDeposit();
		driver.close();
		}
	
	public static void login() throws InterruptedException {
		System.out.println("----->Login: ");
		typeText("//input[@name='uid']", "mngr164606");
		typeText("//input[@name='password']", "uhAgubA");
		click("//input[@name='btnLogin']");
		Thread.sleep(1000);
		System.out.println("User login successfully");
	}
	
	public static void createCustomer() throws InterruptedException {
		name = RandomStringUtils.randomAlphabetic(8);
		System.out.println("----->Create New customer: ");
		//Go to New Customer page
		click("//a[text()='New Customer']");
		
		//Input data
		typeText("//input[@name='name']", name);
		click("//input[@name='rad1'][@value='f']");
		typeText("//input[@name='dob']", "11/11/1997");
		typeText("//textarea[@name='addr']", "Go Vap");
		typeText("//input[@name='city']", "Ho Chi Minh");
		typeText("//input[@name='state']", "VN");
		typeText("//input[@name='pinno']", "134567");
		typeText("//input[@name='telephoneno']", "0943835645");
		typeText("//input[@name='emailid']", name + "@gmail.com");
		typeText("//input[@name='password']", "uyenvy");
		Thread.sleep(1000);
		click("//input[@name='sub']");
		System.out.println("New customer created");
	}
	
	public static void verifyCustomer() {
		System.out.println("***************Verify new customer information***************");
		customer = getText("//td[text()='Customer ID']/following-sibling::td[1]");
	
		checkExist("//p[text()='Customer Registered Successfully!!!']");
		assertTextEquals("CustomerName", "//td[text()='Customer Name']/following-sibling::td[1]", name);
		assertTextEquals("Gender", "//td[text()='Gender']/following-sibling::td[1]", "female");
		assertTextEquals("Birthdate", "//td[text()='Birthdate']/following-sibling::td[1]", "1997-11-11");
		assertTextEquals("Address", "//td[text()='Address']/following-sibling::td[1]", "Go Vap");
		assertTextEquals("City", "//td[text()='City']/following-sibling::td[1]", "Ho Chi Minh");
		assertTextEquals("State", "//td[text()='State']/following-sibling::td[1]", "VN");
		assertTextEquals("Pin", "//td[text()='Pin']/following-sibling::td[1]", "134567");
		assertTextEquals("Mobile", "//td[text()='Mobile No.']/following-sibling::td[1]", "0943835645");
		assertTextEquals("Email", "//td[text()='Email']/following-sibling::td[1]", name + "@gmail.com");
	}
	
	public static void craeteNewAccount() throws InterruptedException {
		System.out.println("----->Create New Account");
		//Go to New Account
		click("//a[text()='New Account']");
		//Input data
		typeText("//input[@name='cusid']", customer);
		click("//select[@name='selaccount']");
		click("//option[@value='Current']");
		typeText("//input[@name='inideposit']", "1402522");
		click("//input[@name='button2']");
		Thread.sleep(1000);
		System.out.println("New customer created");
	}
	
	public static void verifyNewAccount() throws InterruptedException {
		System.out.println("***************Verify New Account*************** ");
		checkExist("//p[text()='Account Generated Successfully!!!']");
		assertTextEquals("CustomerID", "//td[text()='Customer ID']/following-sibling::td[1]",customer);
		account = getText("//td[text()='Account ID']/following-sibling::td[1]");
	}
	
	public static void createAmountDeposit() throws InterruptedException {
		System.out.println("----->Create Amount Deposit");
		//Go to Deposit page
		click("//a[text()='Deposit']");
		typeText("//input[@name='accountno']", account);
		typeText("//input[@name='ammount']", "120000");
		typeText("//input[@name='desc']", "Saving");
		click("//input[@name='AccSubmit']");
		Thread.sleep(1000);
		System.out.println("Amount Deposit created");
	}
	
	public static void verifyAmountDeposit() throws InterruptedException {
		System.out.println("***************Verify Amount Deposit***************");
		checkExist("//p[contains(text(),'Transaction details of Deposit for Account')]");
		assertTextEquals("Account No", "//td[text()='Account No']/following-sibling::td[1]", account);
		assertTextEquals("Description", "//td[text()='Description']/following-sibling::td[1]", "Saving");
		assertTextEquals("Amount", "//td[text()='Amount Credited']/following-sibling::td[1]", "120000");
	}
}
