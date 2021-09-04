package pl.kalghor.weather.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.kalghor.weather.controller.WeatherController;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CityServiceTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void shouldReturnListOfAllCitiesFromDB() {
        City city = new City(null, "Kielce", "20.345", "45.2645");
        City city2 = new City(null, "Warszawa", "20.345", "45.2645");
        City city3 = new City(null, "Katowice", "20.345", "45.2645");
        cityRepository.save(city);
        cityRepository.save(city2);
        cityRepository.save(city3);
        List<City> cityList = new ArrayList<>();

        var cities = cityRepository.findAll();

        cities.forEach(cityList::add);
        assertEquals(3, cityList.size());
        assertEquals(city.getName(), cityList.get(0).getName());
        assertEquals(city2.getName(), cityList.get(1).getName());
        assertEquals(city3.getName(), cityList.get(2).getName());
    }

//    @Test
//    void shouldReturnEmptyListWhenDbIsEmpty() {
//        List<City> cityList = new ArrayList<>();
//
//        var cities = cityRepository.findAll();
//
//        cities.forEach(cityList::add);
//        assertEquals(0, cityList.size());
//    }
//
//    @Test
//    @Rollback
//    void shouldAddCityToDb() {
//        City city = new City(null, "Kielce", "20.345", "45.2645");
//
//        cityRepository.save(city);
//        var cityFromDb = cityRepository.findCityByName("Kielce");
//
//        assertEquals(city.getName(), cityFromDb.getName());
//    }

}