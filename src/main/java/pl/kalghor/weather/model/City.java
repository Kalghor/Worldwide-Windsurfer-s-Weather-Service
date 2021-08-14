package pl.kalghor.weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "latitude")
    public String latitude;

    @Column(name = "longitude")
    public String longitude;

}
