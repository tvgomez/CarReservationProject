package cst438.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import cst438.domain.*;
import cst438.repositories.*;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	//Creates carInfo object from carObject's id
	public CarInfo getCarInfo(int id) {
		
		//Creates list of cars via Car repository's find by ID if is empty or null returns null
		List<Car> cars = carRepository.findById(id);
		if(cars!=null && !cars.isEmpty()) {
			
			//Grabs first car in list, creates extra information for CarInfo and returns new CarInfo with extra information attached. 
			Car car = cars.get(0);
			String imageURL = "https://cst438-car-reservation-3.herokuapp.com/images/" + car.getImage();
			double countyTax = 0.02;
			double govFee = .1;
			double salesTax = 0.09;
			double plusCountyTax = (countyTax * car.getRentalPrice());
			double plusGovFee = (govFee * car.getRentalPrice());
			double plusSalesTax = (salesTax * car.getRentalPrice());
			double total = plusCountyTax + plusGovFee + plusSalesTax + car.getRentalPrice();
			
			return new CarInfo(car.getId(), car.getModel(), 
					car.getMake(), car.getYear(),car.getTrany(),car.getCityMPG(),
					car.getHighwayMPG(), car.getCarClass(),car.getFuelType(),
					car.getRentalPrice(), car.getState(), car.getCity(), car.getQuantity(), car.getImage() , plusCountyTax, plusGovFee, plusSalesTax,total, imageURL);
		}else {
			return null;
			}
	}
	
	//Method to return car list of cars available on a city on a certain date. 
	public List<CarInfo> getCarAvList(String city, String start, String end) throws ParseException{
		
		//Creates a car list of all cars available on a city and creates an empty car list. 
		List<Car> cars = carRepository.findByCity(city);
		List<CarInfo> carInfos = new ArrayList<>();
		
		//Adds carInfo object on carInfo list by car's id (to adhere extra information)
		for(Car car:cars) {
			carInfos.add(getCarInfo(car.getId()));
		}
		
		//Creates another empty list of carInfo objects
		List<CarInfo> carsAv = new ArrayList<>();
		
		//checks every car in first CarInfo list for date's available
		//for every car in  carInfo, creates a reservation object with the dates range 
		//if the total number of car reservations exceeds the quantity of that specific car it skips the car
		//otherwise it adds car to cars available list
		//returns list at end
		for(CarInfo car1:carInfos) {
			
			List<Reservation> reservations = reservationRepository.findDateRanges(start, end, car1.getId());
			if(reservations.size() >= car1.getQuantity()) {
				//DO NOTHING
			}else {
				carsAv.add(car1);
			}
		}
		
		return carsAv;
	}
	
	//Method to check if a car is available on a reservation's given date (to last minute check)
	public boolean isCarAv(Reservation reservation) throws ParseException {
		
		//Creates car list of cars via reservation's car_id
		List<Car> cars = carRepository.findById(reservation.getCar_id());
		
		//creates a carInfo list with given carInfo and getCarAvList method
		List<CarInfo>carsAv = getCarAvList(cars.get(0).getCity(), reservation.getDate_start(), reservation.getDate_end());
		
		//if the car is found returns true otherwise returns false. 
		for(CarInfo car: carsAv) {
			if(car.getId() == cars.get(0).getId()){
				return true;
			}
		}
		return false;
	}

}
