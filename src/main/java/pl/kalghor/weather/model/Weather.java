package pl.kalghor.weather.model;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Weather {

    private String city_name;
    private double temp;
    private double wind_spd;
    private String date;

}
