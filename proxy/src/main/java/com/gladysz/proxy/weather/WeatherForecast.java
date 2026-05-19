package com.gladysz.proxy.weather;

public class WeatherForecast implements WeatherService {

    private String weather = "Default weather description";


    public WeatherForecast() throws InterruptedException {

        refreshData();
    }


    public WeatherForecast(boolean skipRefresh) {
        //constructor for proxy - without refreshData()
    }


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



