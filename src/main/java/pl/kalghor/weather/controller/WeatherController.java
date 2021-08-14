package pl.kalghor.weather.controller;

import org.springframework.web.bind.annotation.*;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.model.Weather;
import pl.kalghor.weather.service.CityService;
import pl.kalghor.weather.service.WeatherService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/app")
public class WeatherController {

    private WeatherService weatherService;
    private CityService cityService;

    public WeatherController(WeatherService weatherService, CityService cityService) {
        this.weatherService = weatherService;
        this.cityService = cityService;
    }


    @GetMapping("/today")
    public Weather getCityWithBestWeatherConditionsToday() {
        return weatherService.getBestWeatherToday(cityService.getCities(), LocalDate.now());
    }

    @GetMapping("/{date}")
    public Weather getCityWithBestWeatherConditions(@PathVariable String date) {
        LocalDate day = LocalDate.parse(date);
        LocalDate formattedDate = LocalDate.parse(day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return weatherService.getBestWeatherToday(cityService.getCities(), formattedDate);
    }

    @GetMapping("/addCity/{cityName}/{latitude}/{longitude}")
    public void addCityToDB(@PathVariable String cityName, @PathVariable String latitude, @PathVariable String longitude){
        City city = new City(null, cityName, latitude, longitude);
        cityService.addCity(city);
    }

}
