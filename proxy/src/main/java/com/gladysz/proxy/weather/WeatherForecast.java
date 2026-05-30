package com.gladysz.proxy.weather;

public class WeatherForecast implements WeatherService {

    private String weather = "Default weather description";


    @Override
    public String getWeather() {

        return weather;
    }


    @Override
    public void refreshData() throws InterruptedException {

        Thread.sleep(5000);

        weather = "Refreshed weather description";
    }
}



