package com.gladysz.proxy;

import com.gladysz.proxy.weather.AutoRefreshingWeatherForecast;
import com.gladysz.proxy.weather.LazyWeatherForecastProxy;
import com.gladysz.proxy.weather.WeatherService;

import java.util.Random;


public class ProxyAppWeather {

    public static void main(String[] args) throws InterruptedException {

        long begin  = System.currentTimeMillis();

        Random random = new Random();

        //WeatherService weatherService = new AutoRefreshingWeatherForecast();
        WeatherService weatherService = new LazyWeatherForecastProxy();

        for (int n = 0; n < 5; n++) {

            System.out.println(weatherService.getWeather());

            if (random.nextInt(100) <= 20) {
                weatherService.refreshData();
                System.out.println("Weather refreshed");
            }
            System.out.println("Execution #" + n + " just finished\n");
        }
        long end = System.currentTimeMillis();

        System.out.println("The execution took " + (end - begin) + " [ms]");
    }
}
