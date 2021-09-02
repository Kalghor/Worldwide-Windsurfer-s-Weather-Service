package pl.kalghor.weather.integration.weatherbit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDataDto {

    public String name;
    public String longitude;
    public String latitude;

}
