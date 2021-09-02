package pl.kalghor.weather.service;

import org.springframework.stereotype.Service;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.repository.CityRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Set<City> getCities(){
        Set<City> cities = new HashSet<>();
        var all = cityRepository.findAll();
        all.forEach(cities::add);
        return cities;
    }

    public void addCity(City city){
        cityRepository.save(city);
    }

}
