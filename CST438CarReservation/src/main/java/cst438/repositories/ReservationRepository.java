package cst438.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import cst438.domain.Reservation;

//Repository class to interact with dB 
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	@Query("SELECT r FROM Reservation r ORDER BY id")
	List<Reservation>findAllReservations();
	
	List<Reservation>findById(int id);
	
	List<Reservation>findByEmail(String email);
	
	//Query to get reservations that only happen in between the selected dates and for the selected car_id
	@Query(nativeQuery = true, value = "SELECT * FROM reservations WHERE (:date_start <= date_end) and (:date_end >= date_start) AND car_id = :id")
	List<Reservation>findDateRanges(String date_start, String date_end, int id);
	
	@Transactional
	@Modifying
	@Query("DELETE Reservation r WHERE r.id = ?1")
	void cancelReservation(int id);

}
