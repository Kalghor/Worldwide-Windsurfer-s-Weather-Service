package pl.kalghor.weather.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kalghor.weather.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
