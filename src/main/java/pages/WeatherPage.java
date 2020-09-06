package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.TestBase;

public class WeatherPage extends TestBase {

	@FindBy(xpath="//input[@id='indiceslist-search']")
	private WebElement searchCityTextBox;


	public WeatherPage() {
		PageFactory.initElements(driver,this);
	}

	public void searchCity(String city){
		wait.until(ExpectedConditions.visibilityOf(searchCityTextBox));
		searchCityTextBox.sendKeys(city);
	}
}
