package pl.kalghor.weather.integration.weatherbit.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kalghor.weather.converter.CityConverter;
import pl.kalghor.weather.integration.weatherbit.dto.CityDto;
import pl.kalghor.weather.model.City;

@Component
public class CityClient {

    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://api.positionstack.com/v1/forward";
    private final String API_KEY = "dd4ba1eefd9d2a449192df49c6ea7ed7";
    private CityConverter converter;

    public CityClient(CityConverter converter) {
        this.converter = converter;
    }

    public City getCityWithLatitudeAndLongitudeCoordinates(String cityName, String country) {
        final var cityDto = restTemplate.getForObject(URL + "?access_key={API_KEY}&query={cityName} {country}", CityDto.class, API_KEY, cityName, country);
        return converter.convertDtoToModel(cityDto);
    }

}
