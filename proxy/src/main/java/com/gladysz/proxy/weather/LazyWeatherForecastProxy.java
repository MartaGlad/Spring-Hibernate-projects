package com.gladysz.proxy.weather;


public class LazyWeatherForecastProxy implements WeatherService {

    private final WeatherForecast weatherForecast;


    public LazyWeatherForecastProxy() {

        weatherForecast = new WeatherForecast(true);
    }


    @Override
    public String getWeather() {

        return weatherForecast.getWeather();
    }


    @Override
    public void refreshData() throws InterruptedException {

        weatherForecast.refreshData();
    }
}
