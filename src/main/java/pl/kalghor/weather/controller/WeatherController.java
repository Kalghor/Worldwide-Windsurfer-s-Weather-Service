package pl.kalghor.weather.controller;

import org.springframework.web.bind.annotation.*;
import pl.kalghor.weather.model.Weather;
import pl.kalghor.weather.service.CityService;
import pl.kalghor.weather.service.WeatherService;
import pl.kalghor.weather.integration.weatherbit.client.CityClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/app")
public class WeatherController {

    private WeatherService weatherService;
    private CityService cityService;
    private CityClient cityClient;

    public WeatherController(WeatherService weatherService, CityService cityService, CityClient client) {
        this.weatherService = weatherService;
        this.cityService = cityService;
        this.cityClient = client;
    }


    @GetMapping("/today")
    public Weather getCityWithBestWeatherConditionsToday() {
        return weatherService.getBestWeatherToday(cityService.getCities(), LocalDate.now());
    }

    @GetMapping("/{date}")
    public Weather getCityWithBestWeatherConditions(@PathVariable String date) {
        final var day = LocalDate.parse(date);
        final var formattedDate = LocalDate.parse(day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return weatherService.getBestWeatherToday(cityService.getCities(), formattedDate);
    }

    @GetMapping("/addCity/{cityName}/{country}")
    public void addCityToDB(@PathVariable String cityName, @PathVariable String country){
        final var city = cityClient.getCityWithLatitudeAndLongitudeCoordinates(cityName, country);
        cityService.addCity(city);
    }

}
