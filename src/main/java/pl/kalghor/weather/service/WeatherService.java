package pl.kalghor.weather.service;

import org.springframework.stereotype.Service;
import pl.kalghor.weather.converter.WeatherConverter;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.model.Weather;
import pl.kalghor.weather.web.client.WeatherClient;
import pl.kalghor.weather.web.dto.DataDto;
import pl.kalghor.weather.web.dto.WeatherDto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private WeatherClient weatherClient;
    private WeatherConverter weatherConverter;


    public WeatherService(WeatherClient weatherClient, WeatherConverter weatherConverter) {
        this.weatherClient = weatherClient;
        this.weatherConverter = weatherConverter;
    }

    public Weather getBestWeatherToday(Set<City> cities, LocalDate date) {
        Set<WeatherDto> weatherForCities = weatherClient.getWeatherForCities(cities);
        for (WeatherDto weatherDto : weatherForCities) {
            weatherDto.setData(getWeatherForDate(weatherDto.getData(), date));
        }
        Set<Weather> weatherSet = weatherConverter.convertToModel(weatherForCities);
        return getCityWithBestWeatherConditions(weatherSet);
    }

    public List<DataDto> getWeatherForDate(List<DataDto> data, LocalDate date) {
        return data.stream().filter(dataDto -> dataDto.getValid_date().equals(date.toString())).collect(Collectors.toList());
    }

    public Weather getCityWithBestWeatherConditions(Set<Weather> weatherSet) {
        List<Weather> citiesWithGoodWeatherConditions = weatherSet.stream()
                .filter(weather -> weather.getWind_spd() >= 5 && weather.getWind_spd() <= 18)
                .filter(weather -> weather.getTemp() >= 5 && weather.getTemp() <= 35)
                .collect(Collectors.toList());
        if (citiesWithGoodWeatherConditions.size() > 0) {
            return citiesWithGoodWeatherConditions.stream()
                    .max(Comparator.comparingDouble(o -> bestWeatherConditions(o.getWind_spd(), o.getTemp())))
                    .get();
        } else {
            return new Weather();
        }
    }

    public double bestWeatherConditions(double windSpeed, double temp) {
        return windSpeed * 3 + temp;
    }

}
