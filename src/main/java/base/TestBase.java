package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import util.TestConstants;

public class TestBase {

	protected static WebDriver driver;
	protected static Properties prop;
	protected static WebDriverWait wait;
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;


	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/WeatherReporting.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() throws MalformedURLException {
		String browserName = prop.getProperty("browser");
		String host = prop.getProperty("host");
		ChromeOptions op = new ChromeOptions();
		String completeUrl="http://" + host + ":4445/wd/hub";

		if(browserName.equals("FF")){
			FirefoxOptions op1 = new FirefoxOptions();
			driver=new RemoteWebDriver(new URL(completeUrl),op1);
		}

		driver=new RemoteWebDriver(new URL(completeUrl),op);

	//	driver.manage().window().maximize();
	//	driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestConstants.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}

	public static RequestSpecification buildRequestSpec(String City,String APIKey){
		return requestSpec=new RequestSpecBuilder()
				.setBaseUri(prop.getProperty("baseURI"))
				.addQueryParam("q",City)
				.addQueryParam("appid",APIKey)
				.setContentType(ContentType.JSON)
				.build();
	}

	public static ResponseSpecification buildResponseSpec(){
		return responseSpec= new ResponseSpecBuilder()
				.expectStatusCode(TestConstants.STATUS_200)
				.expectContentType(ContentType.JSON)
				.build();
	}
}

