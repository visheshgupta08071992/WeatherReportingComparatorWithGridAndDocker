package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class WeatherPage extends TestBase {

	@FindBy(xpath="//input[@id='indiceslist-search']")
	private WebElement searchCityTextBox;

	@FindBy(xpath="//li[@class='searched']//td[3][contains(.,'Bengaluru')]/b/text()")
	private WebElement searchedCity;

	@FindBy(xpath="//div[@class='noti_wrap']")
	private WebElement notificationPopup;

	@FindBy(xpath="//div[@class='noti_wrap']//a[@class='notnow']")
	private WebElement notificationPopupNotNowButton;

	public WeatherPage() {
		PageFactory.initElements(driver,this);
	}

	public void searchCity(String city){
		searchCityTextBox.click();
		searchCityTextBox.sendKeys(city);
	}

	public boolean searchedCityIsDisplayed(String city) {
		return driver.findElement(By.xpath("//b[text()='"+city+"']")).isDisplayed();
	}

	public String searchedCityTemprature(String city) {
		return driver.findElement(By.xpath("//li[@class='searched']//td[3]")).getText();
	}

	public boolean isNotificationPopUpDisplayed(){
		return notificationPopup.isDisplayed();
	}

	public void closeNotificationPopupButton(){
		notificationPopupNotNowButton.click();
	}
}
