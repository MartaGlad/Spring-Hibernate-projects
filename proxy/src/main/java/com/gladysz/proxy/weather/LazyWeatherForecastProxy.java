package com.gladysz.proxy.weather;


public class LazyWeatherForecastProxy implements WeatherService {

    private WeatherForecast weatherForecast;


    private WeatherForecast getWeatherForecast() {

        if (weatherForecast == null) {
            weatherForecast = new WeatherForecast();
        }
        return weatherForecast;
    }


    @Override
    public String getWeather() {

        return getWeatherForecast().getWeather();
    }


    @Override
    public void refreshData() throws InterruptedException {

        getWeatherForecast().refreshData();
    }
}
