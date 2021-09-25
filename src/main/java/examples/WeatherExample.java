package examples;

import com.harium.api.weatherdatasource.Weather;
import com.harium.api.weatherdatasource.WeatherDataSourceAPI;

import java.io.IOException;

public class WeatherExample {

    public static void main(String[] args) throws IOException {
        Weather weather = WeatherDataSourceAPI.getData("200.222.1.1");
        System.out.println(weather.temperatureCelsius + " °C");
        System.out.println(weather.temperatureFahrenheit + " °F");
    }
}
