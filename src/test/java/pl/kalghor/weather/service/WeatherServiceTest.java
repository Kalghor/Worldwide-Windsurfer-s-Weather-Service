package pl.kalghor.weather.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kalghor.weather.integration.weatherbit.dto.DataDto;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.model.Weather;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;


    @Test
    void shouldReturnCityWithBestWeatherConditionsToday() {
        Set<City> citySet = new HashSet<>();
        citySet.add(new City(null, "Fortaleza", "20.34524", "40.2343425"));
        citySet.add(new City(null, "Kielce", "30.34524", "60.2343425"));
        citySet.add(new City(null, "Kraków", "10.34524", "20.2343425"));
        var date = LocalDate.now();

        var response = weatherService.getBestWeatherToday(citySet, date);

        assertEquals(new Weather("Fortaleza", 0, 0, date.toString()).getCityName(), response.getCityName());
    }

    @Test
    void shouldReturnForecastForDate() {
        var dataDto = new DataDto(20.0, 15.5, "2021-08-12");
        var dataDto2 = new DataDto(22.0, 12.0, "2021-07-12");
        var dataDto3 = new DataDto(24.0, 10.7, "2021-06-12");
        var dataDtoList = new ArrayList<DataDto>();
        dataDtoList.add(dataDto);
        dataDtoList.add(dataDto2);
        dataDtoList.add(dataDto3);

        var result = weatherService.getWeatherForDate(dataDtoList, LocalDate.of(2021, 07, 12));

        assertEquals(1, result.size());
        assertEquals("2021-07-12", result.get(0).getValid_date());
    }

    @Test
    void shouldReturnEmptyArrayListWhenThereIsNoForecastForGivenDay() {
        var dataDto = new DataDto(20.0, 15.5, "2021-08-12");
        var dataDto2 = new DataDto(22.0, 12.0, "2021-07-12");
        var dataDto3 = new DataDto(24.0, 10.7, "2021-06-12");
        var dataDtoList = new ArrayList<DataDto>();
        dataDtoList.add(dataDto);
        dataDtoList.add(dataDto2);
        dataDtoList.add(dataDto3);

        var result = weatherService.getWeatherForDate(dataDtoList, LocalDate.of(2021, 07, 14));

        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnCityWithBestWeatherConditions() {
        var weather = new Weather("Warszawa", 24.0, 15.0, "2000-01-01");
        var weather2 = new Weather("Kraków", 22.0, 14.0, "2000-01-01");
        var weather3 = new Weather("Kielce", 21.0, 12.0, "2000-01-01");
        var weatherSet = new HashSet<Weather>();
        weatherSet.add(weather);
        weatherSet.add(weather2);
        weatherSet.add(weather3);
        var expected = weather;

        var result = weatherService.getCityWithBestWeatherConditions(weatherSet);

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnNewWeatherObjectWhenWeatherSetIsEmpty() {

        var weatherSet = new HashSet<Weather>();

        var expected = new Weather();

        var result = weatherService.getCityWithBestWeatherConditions(weatherSet);

        assertEquals(expected.getCityName(), result.getCityName());
        assertEquals(expected.getTemp(), result.getTemp());
        assertEquals(expected.getWindSpd(), result.getWindSpd());
        assertEquals(expected.getDate(), result.getDate());
    }

    @Test
    void shouldReturnBestWeatherConditions() {
        var windSpeed = 10.0;
        var temp = 3.0;
        var expected = 33;

        var result = weatherService.bestWeatherConditions(windSpeed, temp);

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnZeroWhenAllVariableEqualsZero() {
        var windSpeed = 0.0;
        var temp = 0.0;
        var expected = 0.0;

        var result = weatherService.bestWeatherConditions(windSpeed, temp);

        assertEquals(expected, result);
    }

}