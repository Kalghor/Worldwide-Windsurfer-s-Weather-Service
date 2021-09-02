package pl.kalghor.weather.integration.weatherbit.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DataDto {

    public double temp;
    public double wind_spd;
    public String valid_date;

}
