package pl.kalghor.weather.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kalghor.weather.model.dto.WeatherDto;

@Component
public class WeatherClient {

    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "https://api.weatherbit.io/v2.0/forecast/";
    private final String API_KEY = "b5ee118d173b478ba7f582d2c4ce98fc";


    public WeatherDto getWeather() {
        WeatherDto forObject = restTemplate.getForObject(URL + "daily?city=Raleigh&key={API_KEY}", WeatherDto.class, API_KEY);
        System.out.println(forObject);
        return forObject;
//        return restTemplate.getForObject(URL + "daily?city=Raleigh&key={API_KEY}", Object.class, API_KEY);
    }
}
