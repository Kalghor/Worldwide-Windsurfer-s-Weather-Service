package pl.kalghor.weather.web.dto;

import lombok.*;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class DataDto {

    public double temp;
    public double wind_spd;
    public String valid_date;

}
