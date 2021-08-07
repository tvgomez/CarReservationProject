package cst438.controllers;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cst438.domain.*;
import cst438.repositories.*;
import cst438.services.*;

@RestController
public class ReservationRestController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarService CarService;
	
	
	//API to display all reservations based on email given
	@GetMapping("/carrental/api/reservations/{email}")
	public ResponseEntity<List <Reservation>> reservationsByEmail(@PathVariable("email")String email) throws ParseException{
		
		///Creates list out of reservation string given, if empty returns NOT_FOUND, returns list and OK otherwise. 
		List<Reservation> reservations = reservationRepository.findByEmail(email);
		for(Reservation reservation:reservations) {
			System.out.println(reservation.getId());
			System.out.println(reservation.getEmail());
			System.out.println(reservation.getCar_id());
			System.out.println(reservation.getDate_start());
			System.out.println(reservation.getDate_end());
		}
		if(reservations.size() == 0) {
			return new ResponseEntity <List <Reservation>>( HttpStatus.NOT_FOUND);
			
		} else {
		
			return new ResponseEntity <List <Reservation>>(reservations, HttpStatus.OK);
		}
	}
	
	//API to display single reservation
	@GetMapping("/carrental/api/reservations/details/{id}")
	public ResponseEntity<Reservation>carInfo(@PathVariable("id")int id) throws ParseException{
		
		///Creates list out of reservation string given, if empty returns NOT_FOUND, returns list and OK otherwise. 
		List<Reservation> reservations = reservationRepository.findById(id);
		for(Reservation reservation:reservations) {
			System.out.println(reservation.getId());
			System.out.println(reservation.getEmail());
			System.out.println(reservation.getCar_id());
			System.out.println(reservation.getDate_start());
			System.out.println(reservation.getDate_end());
		}
		if(reservations.size() == 0) {
			return new ResponseEntity <Reservation>( HttpStatus.NOT_FOUND);
			
		} else {
			Reservation reservation = reservations.get(0);
			return new ResponseEntity <Reservation>(reservation, HttpStatus.OK);
		}
	}
	
	//API to make car reservation
	@PostMapping(path = "/carrental/api/reserve", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) throws ParseException {
		
		//If car is available via the isCarAv boolean method it is saved in dB and returns reservation object and OK. NOT_FOUND otherwise
		if(CarService.isCarAv(reservation)) {
			
			System.out.println(reservation.getId());
			System.out.println(reservation.getEmail());
			System.out.println(reservation.getCar_id());
			System.out.println(reservation.getDate_start());
			System.out.println(reservation.getDate_end());

			reservationRepository.save(reservation);
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}else {
			return new ResponseEntity <Reservation>( HttpStatus.NOT_FOUND);
		}
				
	}
	
	//API to cancel car reservation
	@PostMapping(path = "/carrental/api/cancel", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Reservation> cancelReservation(@RequestBody ReservationInfo reservationInfo) throws ParseException {
		
		//Creates reservation list from reservationInfo object's id
		//if list is null returns NOT_FOUND otherwise it grabs first reservation in list
		//if list is 0 returns NOT_FOUND
		//if username does not equal reservation's email returns FORBIDDEN otherwise returns object and OK.
		List<Reservation> reservations = reservationRepository.findById(reservationInfo.getId());
		if(reservations == null){
			return new ResponseEntity <Reservation>( HttpStatus.NOT_FOUND);
		}
		
		Reservation reservation = reservations.get(0);
		for(Reservation reservation1:reservations) {
			System.out.println(reservation1.getId());
			System.out.println(reservation1.getEmail());
			System.out.println(reservation1.getCar_id());
			System.out.println(reservation1.getDate_start());
			System.out.println(reservation1.getDate_end());
		}
		if(reservations.size() == 0) {
			return new ResponseEntity <Reservation>( HttpStatus.NOT_FOUND);
		}
		else if(!(reservations.get(0).getEmail().equals(reservationInfo.getUsername()))) {
			return new ResponseEntity <Reservation>( HttpStatus.FORBIDDEN);
		}else {
			reservationRepository.cancelReservation(reservationInfo.getId());
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}
				
	}

}
