package pl.kalghor.weather.converter;

import org.springframework.stereotype.Component;
import pl.kalghor.weather.model.City;
import pl.kalghor.weather.integration.weatherbit.dto.CityDto;

@Component
public class CityConverter {

    public City convertDtoToModel(CityDto cityDto) {
        final var city = new City();
        final var data = cityDto.getData().get(0);
        city.setName(data.getName());
        city.setLongitude(data.getLongitude());
        city.setLatitude(data.getLatitude());
        return city;
    }

}
