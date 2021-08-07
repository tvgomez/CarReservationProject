package cst438.controllers;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import cst438.domain.*;
import cst438.repositories.*;
import cst438.services.*;

@RestController
public class CarRestController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarService CarService;
	
	//API to obtain car lists of cars available by city on a certain date
	@GetMapping("/carrental/api/carsByCity/{city}/{start_date}/{end_date}")
	public ResponseEntity<List <CarInfo>> carsByCity(@PathVariable("city")String city,@PathVariable("start_date")String start_date,@PathVariable("end_date")String end_date) throws ParseException{
		
		//Creates car list from dB using car_id
		List<Car> cars = carRepository.findByCity(city);
		
		for(Car car: cars) {
			System.out.println(car.getId());
			System.out.println(car.getModel());
		}
		//Checks if list is empty returns NOT_FOUND
		if(cars.size() == 0) {
			return new ResponseEntity <List <CarInfo>>( HttpStatus.NOT_FOUND);
			
		} else {
			//if list is not empty calls on service method to check if car is available on date. Returns list and OK
			List<CarInfo>carsAv = CarService.getCarAvList(city, start_date, end_date);
			for(CarInfo car: carsAv) {
				System.out.println(car.getId());
				System.out.println(car.getModel());
		}
		return new ResponseEntity <List <CarInfo>>(carsAv, HttpStatus.OK);
		}
	}
	
	//API to display a single car's detail 
	@GetMapping("/carrental/api/carsByCity/details/{id}")
	public ResponseEntity<CarInfo>carInfo(@PathVariable("id")int id) throws ParseException{
		
		//If car_id is not in range of car_id length returns NOT_FOUND otherwise it returns the object info and OK
		if(id < 1 || id > 388) {
			return new ResponseEntity <CarInfo>( HttpStatus.NOT_FOUND);
			
		} else {
			CarInfo carInfo = CarService.getCarInfo(id);
			System.out.println(carInfo.getId());
			System.out.println(carInfo.getModel());
		
		return new ResponseEntity <CarInfo>(carInfo, HttpStatus.OK);
		}
	}

}
