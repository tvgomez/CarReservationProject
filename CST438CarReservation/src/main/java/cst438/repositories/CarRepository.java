package cst438.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import cst438.domain.Car;

//Repository class to interact with dB 
public interface CarRepository extends JpaRepository<Car, Integer>{
	
	@Query("SELECT c FROM Car c ORDER BY model")
	List<Car> findByModel();
	
	@Query(nativeQuery = true, value = "SELECT * FROM car ORDER BY RAND() LIMIT 5")
	List<Car> randomFive();
	
	List<Car> findById(int id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM car  WHERE car.city LIKE ?1")
	List<Car> findByCity(String city);
	
}
