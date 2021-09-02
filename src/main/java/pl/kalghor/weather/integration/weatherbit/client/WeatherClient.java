package pl.kalghor.weather.integration.weatherbit.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.integration.weatherbit.dto.WeatherDto;

import java.util.HashSet;
import java.util.Set;

@Component
public class WeatherClient {

    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "https://api.weatherbit.io/v2.0/forecast/";
    private final String API_KEY = "b5ee118d173b478ba7f582d2c4ce98fc";


    public Set<WeatherDto> getWeatherForCities(Set<City> cities) {
        final Set<WeatherDto> forecasts = new HashSet<>();
        for (City c : cities) {
            if (c.name.contains(" ")) {
                final var dto = restTemplate.getForObject(URL + "daily?&lat={lat}&lon={lon}&key={API_KEY}", WeatherDto.class, c.latitude, c.longitude, API_KEY);
                forecasts.add(dto);
            } else {
                final var dto = restTemplate.getForObject(URL + "daily?city={city}&key={API_KEY}", WeatherDto.class, c.name, API_KEY);
                forecasts.add(dto);
            }
        }
        return forecasts;
    }
}
