package com.gladysz.proxy.weather;

public class AutoRefreshingWeatherForecast extends WeatherForecast {

    public AutoRefreshingWeatherForecast() throws InterruptedException {

        refreshData();
    }
}
