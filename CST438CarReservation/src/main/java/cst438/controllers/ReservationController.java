package cst438.controllers;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import cst438.domain.*;
import cst438.repositories.*;
import cst438.services.CarService;

@Controller
public class ReservationController {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarService CarService;
	

	///Saves current reservation in dB 
	@PostMapping("/carrental/carsByCity/reserved/checkout")
	public String processCarRental(@Valid Reservation reservation, BindingResult result, Model model) {
		
		System.out.println(reservation.getId());
		System.out.println(reservation.getEmail());
		System.out.println(reservation.getCar_id());
		System.out.println(reservation.getDate_start());
		System.out.println(reservation.getDate_end());
		//if error checks for errors
		if(result.hasErrors()) {
			System.out.println("Error!!!!");
			return "car_checkout";
		}
		
		//Repository method to save to dB
		reservationRepository.save(reservation);

		//Appends all attributes to model and call on site to display
		model.addAttribute("reservation", reservation);
		return "reservation_confirmed";
	}
	
	//Method to validate credentials and display current reservation
	@PostMapping("/carrental/checkReservation")
	public String viewCarRental(@Valid Reservation reservation, @RequestParam("email") String email,@RequestParam("id") int id,  BindingResult result, Model model) {

		
		//if error checks for errors
		if(result.hasErrors()) {
			System.out.println("Error!!!!");
			return "car_checkout";
		}
	
		//Finds reservation via id if not found sends user to not found site
		List<Reservation> reservations = reservationRepository.findById(id);

		if(reservations == null) 
			return "resevation_Not_Found";	
		
		//creates reservation object from first reservation in list 
		Reservation reservation1 = reservations.get(0);
		System.out.println(reservation.getId());
		System.out.println(reservation.getEmail());
		System.out.println(reservation.getCar_id());
		System.out.println(reservation.getDate_start());
		System.out.println(reservation.getDate_end());
		//if email on reservation does not match email on parameter displays wrong credentials page
		if(!(reservation1.getEmail().equals(email)))
				return "wrong_Credentials";
		
		//creates carInfo object from id
		CarInfo car = CarService.getCarInfo(id);
		
		//Appends all attributes to model and call on site to display
		model.addAttribute("car", car);
		model.addAttribute("reservation" , reservation1);
		return "reservation_details";
	}
	
	///Method for deleting reservation
	@GetMapping("/carrental/deleteReservation/{id}")
	public String deleteReservation(@PathVariable("id") int id, Model model) {
		
		//creates reservation list object and deletes from dB based on id given 
		List<Reservation> reservations = reservationRepository.findById(id);
		Reservation reservation = reservations.get(0);
		System.out.println(reservation.getId());
		System.out.println(reservation.getEmail());
		System.out.println(reservation.getCar_id());
		System.out.println(reservation.getDate_start());
		System.out.println(reservation.getDate_end());
		reservationRepository.cancelReservation(id);
		return "reservation_deleted";
	}
	
	///TEST controller
	/*@GetMapping("/allReservations")
	public String getAllReservations(Model model) {
		

		List<Reservation> reservations = reservationRepository.findAllReservations();
		
		List<Reservation> reservationCheck = reservationRepository.findByEmail("mrleos4misswalls@hotmail.com");
		for(Reservation reservation: reservationCheck) {
			System.out.println(reservation.getId());
			System.out.println(reservation.getEmail());
			System.out.println(reservation.getCar_id());
			System.out.println(reservation.getDate_start());
			System.out.println(reservation.getDate_end());
		}
		model.addAttribute("reservations", reservations);
		
		return "reservation_confirmed";
	}*/
}
