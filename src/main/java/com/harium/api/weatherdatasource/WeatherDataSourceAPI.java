package com.harium.api.weatherdatasource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class WeatherDataSourceAPI {

    private static final String BASE_URL = "https://weatherdatasource.com/";
    private static final int TIMEOUT = 10000;

    public static Weather getData(String ip) throws IOException {
        return getData(ip, TIMEOUT);
    }

    /**
     *
     * @param ip - IPv4
     * @return the place
     */
    public static Weather getData(String ip, int timeout) throws IOException {
        Weather weather = new Weather();

        URL url = new URL(BASE_URL + "/" + ip);
        Document document = Jsoup.parse(url, timeout);

        weather.temperatureCelsius = getCelsius(document);
        weather.temperatureFahrenheit = getFahrenheit(document);
        weather.windSpeed = getWindSpeed(document);
        weather.windDirection = getWindDirection(document);
        weather.sunset = getSunset(document);
        weather.sunrise = getSunrise(document);

        return weather;
    }

    private static String getCelsius(Document document) {
        String temperature = document.select("span").get(2).text();
        return temperature.substring(0, temperature.length() - 3);
    }

    private static String getFahrenheit(Document document) {
        String temperature = document.select("span").get(0).text();
        return temperature.substring(0, temperature.length() - 3);
    }

    private static String getWindDirection(Document document) {
        String data = document.select("p").get(2).text();
        if (!data.contains(": ")) {
            return "";
        }
        return data.split(": ")[1];
    }

    private static String getWindSpeed(Document document) {
        String data = document.select("p").get(3).text();
        if (!data.contains(": ")) {
            return "";
        }
        return data.split(": ")[1];
    }

    private static String getSunset(Document document) {
        String data = document.select("p").get(5).text();
        if (!data.contains(": ")) {
            return "";
        }
        return data.split(": ")[1];
    }

    private static String getSunrise(Document document) {
        String data = document.select("p").get(4).text();
        if (!data.contains(": ")) {
            return "";
        }
        return data.split(": ")[1];
    }

}
