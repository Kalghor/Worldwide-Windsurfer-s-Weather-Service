package pl.kalghor.weather.web.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.web.dto.WeatherDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class WeatherClient {

    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "https://api.weatherbit.io/v2.0/forecast/";
    private final String API_KEY = "b5ee118d173b478ba7f582d2c4ce98fc";


    public Set<WeatherDto> getWeatherForCities(Set<City> cities) {
        Set<WeatherDto> forecasts = new HashSet<>();
        for(City c : cities){
            WeatherDto dto = restTemplate.getForObject(URL + "daily?city={city}}&key={API_KEY}", WeatherDto.class, c.name, API_KEY);
            forecasts.add(dto);
        }
        return forecasts;
    }
}
