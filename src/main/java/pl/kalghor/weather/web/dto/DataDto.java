package pl.kalghor.weather.model.dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DataDto {

    public String temp;
    public String wind_spd;

}
