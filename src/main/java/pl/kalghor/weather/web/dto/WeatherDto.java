package pl.kalghor.weather.model.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class WeatherDto {

    public String city_name;
    public List<DataDto> data;
//    public DataDto data;

}
