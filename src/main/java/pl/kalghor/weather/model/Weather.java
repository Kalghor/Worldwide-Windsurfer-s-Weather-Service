package pl.kalghor.weather.model;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Weather {

    private String cityName;
    private double temp;
    private double windSpd;
    private String date;

}
