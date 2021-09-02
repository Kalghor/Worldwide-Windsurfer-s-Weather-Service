package pl.kalghor.weather.integration.weatherbit.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class WeatherDto {

    private String city_name;
    private List<DataDto> data;

}
