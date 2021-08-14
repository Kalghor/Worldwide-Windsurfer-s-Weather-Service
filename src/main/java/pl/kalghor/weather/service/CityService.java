package pl.kalghor.weather.service;

import org.springframework.stereotype.Service;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.repository.CityRepository;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    
}
