package pl.kalghor.weather.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kalghor.weather.model.Weather;
import pl.kalghor.weather.integration.weatherbit.dto.DataDto;
import pl.kalghor.weather.integration.weatherbit.dto.WeatherDto;

import java.util.ArrayList;
import java.util.HashSet;


class WeatherConverterTest {

    @Test
    void shouldConvertDtoClassToModel() {
        //given
        WeatherConverter weatherConverter = new WeatherConverter();

        var weatherDto = new WeatherDto();
        var dataDtoList = new ArrayList<DataDto>();
        var dataDto = new DataDto();
        var weatherDtoSet = new HashSet<WeatherDto>();

        weatherDto.setCity_name("Warszawa");

        dataDto.setTemp(26.0);
        dataDto.setWind_spd(7.4);
        dataDto.setValid_date("2000-01-01");

        dataDtoList.add(dataDto);

        weatherDto.setData(dataDtoList);
        weatherDtoSet.add(weatherDto);

        var expected = new Weather("Warszawa", 26.0, 7.4, "2000-01-01");

        //when
        var weathers = weatherConverter.convertToModel(weatherDtoSet);


        //then
        for(Weather weather : weathers){
            Assertions.assertEquals(weather.getTemp(), expected.getTemp());
            Assertions.assertEquals(weather.getWindSpd(), expected.getWindSpd());
            Assertions.assertEquals(weather.getCityName(), expected.getCityName());
            Assertions.assertEquals(weather.getDate(), expected.getDate());
        }
    }

    @Test
    void shouldReturnEmptyWeatherObjectWhenConvertEmptyObject() {
        //given
        WeatherConverter weatherConverter = new WeatherConverter();

        var weatherDto = new WeatherDto();
        var dataDtoList = new ArrayList<DataDto>();
        var dataDto = new DataDto();
        var weatherDtoSet = new HashSet<WeatherDto>();

        dataDtoList.add(dataDto);

        weatherDto.setData(dataDtoList);
        weatherDtoSet.add(weatherDto);

        var expected = new Weather();

        //when
        var weathers = weatherConverter.convertToModel(weatherDtoSet);


        //then
        for(Weather weather : weathers){
            Assertions.assertEquals(weather.getTemp(), expected.getTemp());
            Assertions.assertEquals(weather.getWindSpd(), expected.getWindSpd());
            Assertions.assertEquals(weather.getCityName(), expected.getCityName());
            Assertions.assertEquals(weather.getDate(), expected.getDate());
        }
    }
}