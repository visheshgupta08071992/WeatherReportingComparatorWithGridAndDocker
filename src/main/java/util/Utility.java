package util;

public class Utility {

	public static double getTemperatureInCelcius(String completeTemperature){
		String [] completeTempuratureArray=completeTemperature.split("\\)");
		String newTemp=completeTempuratureArray[0];
		String [] newTemp2=newTemp.split("\\(");
		String newTemp3=newTemp2[1];
		String [] newTemp4=newTemp3.split("°C");
		String newTemp5=newTemp4[0];
		double temperatureInCelcius=convertStringtoNumeric(newTemp5);
		return temperatureInCelcius;
	}

	public static double getTemperatureInKelvin(double temperatureInCelcius){
		double temperatureInKelvin=temperatureInCelcius + 273.15;
		return temperatureInKelvin;
	}

	public static double convertStringtoNumeric(String temperature){
		return Double.parseDouble(temperature);
	}
}
