package pl.kalghor.weather.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kalghor.weather.integration.weatherbit.dto.CityDataDto;
import pl.kalghor.weather.integration.weatherbit.dto.CityDto;
import pl.kalghor.weather.model.City;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class CityConverterTest {

    @Autowired
    private CityConverter cityConverter;

    @Test
    void convertDtoToModel() {
        var cityDto = new CityDto();
        var cityDataDto = new CityDataDto();
        cityDataDto.setName("Warszawa");
        cityDataDto.setLongitude("20.3456");
        cityDataDto.setLatitude("50.3452");
        var cityDtoList = new ArrayList<CityDataDto>();
        cityDtoList.add(cityDataDto);
        cityDto.setData(cityDtoList);
        City expected = new City(null, "Warszawa", "50.3452", "20.3456");

        var city = cityConverter.convertDtoToModel(cityDto);

        assertEquals(expected.getName(), city.getName());
        assertEquals(expected.getLatitude(), city.getLatitude());
        assertEquals(expected.getLongitude(), city.getLongitude());
    }

    @Test
    void shouldReturnNewCityObjectWhenCityDataDtoIsEmpty() {
        var cityDto = new CityDto();
        var cityDataDto = new CityDataDto();
        var cityDtoList = new ArrayList<CityDataDto>();
        cityDtoList.add(cityDataDto);
        cityDto.setData(cityDtoList);
        City expected = new City();

        var city = cityConverter.convertDtoToModel(cityDto);

        assertEquals(expected.getName(), city.getName());
        assertEquals(expected.getLatitude(), city.getLatitude());
        assertEquals(expected.getLongitude(), city.getLongitude());
    }
}