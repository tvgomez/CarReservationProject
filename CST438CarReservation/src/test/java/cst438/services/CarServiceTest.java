package cst438.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Matchers.anyInt;
import cst438.repositories.CarRepository;
import cst438.repositories.ReservationRepository;
import cst438.domain.Car;
import cst438.domain.CarInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;


public class CarServiceTest {
	
	//This test involves Mock, MockBean and InjectMocks all used to show different testing packages in JUnit
	@Mock
	private CarRepository carRepository;
	@MockBean
	private ReservationRepository reservationRepository;
	@InjectMocks
	private CarService carService;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
		
	@Test
	public void testCarFound() throws Exception{
		
		
		//Given data this data is dummy inputed to mimic our service 
		int id = 1;
		String model = "Crv";
		String make = "Honda";
		int year = 2019;
		String trany = "Auto";
		double cityMPG = 33.0;
		double highwayMPG = 33.5;
		String carClass = "Economy";
		String fuelType = "Regular";
		double rentalPrice = 22.25;
		String state = "CA";
		String city = "Salinas";
		int quantity = 1;
		String image = "Car";
		double countyTax = 0.02;
		double govFee = .1;
		double salesTax = 0.09;
		double plusCountyTax = (countyTax * rentalPrice);
		double plusGovFee = (govFee * rentalPrice);
		double plusSalesTax = (salesTax * rentalPrice);
		double total = plusCountyTax + plusGovFee + plusSalesTax + rentalPrice;
		
		
		Car car1 = new Car(id,model,make,year,trany,cityMPG,highwayMPG,carClass,fuelType,rentalPrice,state,city,quantity,image);
		List<Car> cars = new ArrayList<Car>();
		
		System.out.println(car1.getImage());
		cars.add(car1);
		String imageURL = "https://cst438-car-reservation-3.herokuapp.com/images/" + car1.getImage();

		
		//This is the mock test I am asking that when carRepository method is called upon using anyInt then return the object I have created. 
		//There is a actual and expected value created
		//When
		when(carRepository.findById(anyInt())).thenReturn(cars);
		
		CarInfo actual = carService.getCarInfo(id);
		
		CarInfo expected = new CarInfo(id,model,make,year,trany,cityMPG,highwayMPG,carClass,fuelType,rentalPrice,state,city,quantity,image,plusCountyTax, plusGovFee, plusSalesTax,total, imageURL);
		//then		
		assertEquals(expected,actual);
		
		
	}
	
	//The creation of this test helped us find a error in our logic
	//If and else statement created in our service for carService to able to return a null value
	@Test
	public void testCarsNotFound(){
		List<Car> cars = new ArrayList<Car>();
		Car empty = new Car();
		
		//given
		cars.add(empty);
		
		//when
		when(carRepository.findById(anyInt())).thenReturn(null);
		
		CarInfo actual = carService.getCarInfo(0);
		//Then
		assertNull(actual);
		
	}
	
	
	@Test
	public void multTestCarsFound(){
		int id = 1;
		String model = "Crv";
		String make = "Honda";
		int year = 2019;
		String trany = "Auto";
		double cityMPG = 33.0;
		double highwayMPG = 33.5;
		String carClass = "Economy";
		String fuelType = "Regular";
		double rentalPrice = 22.25;
		String state = "CA";
		String city = "Salinas";
		int quantity = 1;
		String image = "Car";
		double countyTax = 0.02;
		double govFee = .1;
		double salesTax = 0.09;
		double plusCountyTax = (countyTax * rentalPrice);
		double plusGovFee = (govFee * rentalPrice);
		double plusSalesTax = (salesTax * rentalPrice);
		double total = plusCountyTax + plusGovFee + plusSalesTax + rentalPrice;
		String imageURL = "https://cst438-car-reservation-3.herokuapp.com/images/Car";
		
		//Given
		Car car1 = new Car(id,model,make,year,trany,cityMPG,highwayMPG,carClass,fuelType,rentalPrice,state,city,quantity,image);
		Car car2 = new Car(1,"Bmw","m3",1999,"auto",32.5,40.2,"Lux","Prem",44.5,"Tx","Austin",1,"Sport");
		List<Car> cars = new ArrayList<Car>();
		
	
		cars.add(car1);
		cars.add(car2);
		
		//When
		when(carRepository.findById(anyInt())).thenReturn(cars);
		
		CarInfo actual = carService.getCarInfo(id);
		
		CarInfo expected = new CarInfo(id,model,make,year,trany,cityMPG,highwayMPG,carClass,fuelType,rentalPrice,state,city,quantity,image,plusCountyTax, plusGovFee, plusSalesTax,total, imageURL);
		
		//Then
		assertEquals(expected,actual);
		
	}
	
}

	
