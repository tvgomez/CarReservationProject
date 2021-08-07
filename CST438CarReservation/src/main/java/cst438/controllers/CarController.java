package cst438.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import cst438.domain.*;
import cst438.repositories.*;
import cst438.services.*;


@Controller
public class CarController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarService CarService;
	
	//Main landing page for the site creates blank objects appends them to site
	@GetMapping("/carrental")
	public String displayCities(Model model) {
		Reservation resevation = new Reservation();
		Car car = new Car();
		model.addAttribute("reservation", resevation);
		model.addAttribute("car", car);
		return "car_rental";
	}
	
	//Searches for cars by selected city and date range, appends information to cars_by_city page. 
	@PostMapping("/carrental/carsByCity")
	public String displayCarsByCity(@RequestParam("city")String city,@RequestParam("start")String start,@RequestParam("end")String end, Model model) throws ParseException {

		//Date format for dB 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		//Code to make sure start date happens before end date. 
		Date date1 = format.parse(start);
		Date date2 = format.parse(end);
		
		String date_start;
		String date_end;
		if (!(date1.compareTo(date2) <= 0)) {
			date_start = end;
			date_end = start;
		}else {
			date_start = start;
			date_end = end;
		}
		
		//Calls on formula from Car service to check which cars are available which dates. 
		List<CarInfo>carsAv = CarService.getCarAvList(city, date_start, date_end);
		
		for(CarInfo car: carsAv) {
			System.out.println(car.getId());
			System.out.println(car.getModel());
		}
		//Appends all attributes to model and call on site to display
		model.addAttribute("car", carsAv);
		model.addAttribute("date_start", date_start);
		model.addAttribute("date_end", date_end);
		return "car_by_city";
		
	}
	
	//Searches for a specific car to display with reservation details
	@PostMapping("/carrental/carsByCity/reserved")
	public String carReserved(@RequestParam("id") int id,@RequestParam("date_start") String date_start,@RequestParam("date_end") String date_end, Model model) {
		
		//Creates car object by looking at dB via car_id
		CarInfo car = CarService.getCarInfo(id);
		
		System.out.println(car.getId());
		System.out.println(car.getModel());
		
		Reservation reservation = new Reservation();
		
		//Sets a reservation's object start and end dates
		reservation.setDate_start(date_start);
		reservation.setDate_end(date_end);
		
		//Appends all attributes to model and call on site to display
		model.addAttribute("car", car);
		model.addAttribute("reservation" , reservation);
		
		return "car_checkout";
	}
	
	
	//Gets a single car object and displays details on it
	@GetMapping("/carrental/carsByCity/details/{id}")
	public String displayCarDetails(@PathVariable("id") int id,  Model model) {
		
		//Finds car in dB via car_id
		List<Car> cars = carRepository.findById(id);
		
		Car car = cars.get(0);
		
		System.out.println(car.getId());
		System.out.println(car.getModel());
		//Appends all attributes to model and call on site to display
		model.addAttribute("car", car);
		
		return "car_info";
		
	}
	
	//Testing controllers 
	
	/*
	@GetMapping("/allCars")
	public String getAllCars(Model model) {
		List<Car> cars = carRepository.findByModel();

		model.addAttribute("car", cars);
		
		return "cars_list";
		
	}
	@GetMapping("/randomFiveCars")
	public String geFiveRandomCars(Model model) {
		List<Car> randCars = carRepository.randomFive();
		model.addAttribute("car", randCars);
		
		return "cars_info_five";
		
	}*/
}