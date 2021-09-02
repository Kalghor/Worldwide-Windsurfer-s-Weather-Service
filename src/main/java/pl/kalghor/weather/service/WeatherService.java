package pl.kalghor.weather.service;

import org.springframework.stereotype.Service;
import pl.kalghor.weather.converter.WeatherConverter;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.model.Weather;
import pl.kalghor.weather.integration.weatherbit.client.WeatherClient;
import pl.kalghor.weather.integration.weatherbit.dto.DataDto;
import pl.kalghor.weather.integration.weatherbit.dto.WeatherDto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherConverter weatherConverter;


    public WeatherService(WeatherClient weatherClient, WeatherConverter weatherConverter) {
        this.weatherClient = weatherClient;
        this.weatherConverter = weatherConverter;
    }

    public Weather getBestWeatherToday(Set<City> cities, LocalDate date) {
        final var weatherForCities = weatherClient.getWeatherForCities(cities);
        for (WeatherDto weatherDto : weatherForCities) {
            weatherDto.setData(getWeatherForDate(weatherDto.getData(), date));
        }
        final Set<Weather> weatherSet = weatherConverter.convertToModel(weatherForCities);
        return getCityWithBestWeatherConditions(weatherSet);
    }

    public List<DataDto> getWeatherForDate(List<DataDto> data, LocalDate date) {
        return data.stream().filter(dataDto -> dataDto.getValid_date().equals(date.toString())).collect(Collectors.toList());
    }

    public Weather getCityWithBestWeatherConditions(Set<Weather> weatherSet) {
        final var citiesWithGoodWeatherConditions = weatherSet.stream()
                .filter(weather -> weather.getWindSpd() >= 5 && weather.getWindSpd() <= 18)
                .filter(weather -> weather.getTemp() >= 5 && weather.getTemp() <= 35)
                .collect(Collectors.toList());
        if (citiesWithGoodWeatherConditions.size() > 0) {
            return citiesWithGoodWeatherConditions.stream()
                    .max(Comparator.comparingDouble(o -> bestWeatherConditions(o.getWindSpd(), o.getTemp())))
                    .get();
        } else {
            return new Weather();
        }
    }

    public double bestWeatherConditions(double windSpeed, double temp) {
        return windSpeed * 3 + temp;
    }

}
