package pl.kalghor.weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "city")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class City {

    @Id
    @GeneratedValue( generator = "UUID" )
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    public UUID id;

    @Column(name = "name")
    public  String name;

    @Column(name = "latitude")
    public String latitude;

    @Column(name = "longitude")
    public String longitude;

}
