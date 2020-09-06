import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pages.WeatherPage;
import util.TestConstants;
import util.Utility;

public class WeatherPageTest extends TestBase {

	public WeatherPage weatherPage;
	public double temperatureInKelvinFromUI;
	public double temperatureInKelvinfromAPI;
	public double variance;

	public WeatherPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp(){
		initialization();
		weatherPage=new WeatherPage();
		if(weatherPage.isNotificationPopUpDisplayed()){
			weatherPage.closeNotificationPopupButton();
		}
	}

	@Test(dataProvider = "cityData")
	public void verifyVarianceInTemperatureFromUIAndAPI(String City){
		weatherPage.searchCity(City);
		Assert.assertTrue(weatherPage.searchedCityIsDisplayed(City));
		String completeTempurature=weatherPage.searchedCityTemprature(City);
		double temperatureInCelcius=Utility.getTemperatureInCelcius(completeTempurature);
		temperatureInKelvinFromUI=Utility.getTemperatureInKelvin(temperatureInCelcius);
		Response response=RestAssured.given().log().all()
				.spec(TestBase.buildRequestSpec(City,TestConstants.API_KEY))
				.when().post()
				.then().log().all()
				.spec(TestBase.buildResponseSpec())
				.extract().response();
		String temperature=response.jsonPath().getString("main.temp");
		temperatureInKelvinfromAPI= Utility.convertStringtoNumeric(temperature);
		variance=Math.abs(temperatureInKelvinfromAPI-temperatureInKelvinFromUI);
		Assert.assertTrue(variance<=2.00,
				"\nTemperature Variance is more than 2 for city : " + City + "\nTemparture Through UI is : " +
				temperatureInKelvinFromUI +
						"  " +
						"\nTemperature through API is : " + temperatureInKelvinfromAPI + "\nVariance is : " + variance);
	}

	@DataProvider(name="cityData")
	Object[][] getCities() throws IOException {
		String [][] cities={{"Pune"},{"Bengaluru"},{"Chennai"},{"Chandigarh"},{"Nagpur"},{"Indore"}};
		return (cities);
	}


	@AfterMethod
	public void shutDown(){
		driver.quit();
	}
}
