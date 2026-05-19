package com.gladysz.proxy.weather;

public interface WeatherService {

    String getWeather();

    void refreshData() throws InterruptedException;
}
