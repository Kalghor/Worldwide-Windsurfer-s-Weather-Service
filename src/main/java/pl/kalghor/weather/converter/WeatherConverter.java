package pl.kalghor.weather.converter;

import org.springframework.stereotype.Component;
import pl.kalghor.weather.model.Weather;
import pl.kalghor.weather.web.dto.WeatherDto;

import java.util.HashSet;
import java.util.Set;

@Component
public class WeatherConverter {

    public Set<Weather> convertToModel(Set<WeatherDto> weatherDtoSet) {
        Set<Weather> weatherSet = new HashSet<>();
        for (WeatherDto weatherDto : weatherDtoSet) {
            weatherSet.add(convertToModel(weatherDto));
        }
        return weatherSet;
    }

    private Weather convertToModel(WeatherDto weatherDto) {
        Weather weather = new Weather();
        weather.setCity_name(weatherDto.getCity_name());
        weather.setTemp(weatherDto.getData().get(0).getTemp());
        weather.setWind_spd(weatherDto.getData().get(0).getWind_spd());
        weather.setDate(weatherDto.getData().get(0).getValid_date());
        return weather;
    }

}
