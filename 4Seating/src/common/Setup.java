package common;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Setup {
	
	public static WebDriver driver;
	public static ArrayList<String> line = new ArrayList<String>();
	public static Logger log = Logger.getLogger("");
	static String path = "src/properties/log4j.properties";


	@BeforeSuite
	public static void setup() throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		PropertyConfigurator.configure(path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@AfterSuite
	public static void Teardown() throws IOException {
		//log.info("******Test Done******");
		driver.quit();
	}
	
}
