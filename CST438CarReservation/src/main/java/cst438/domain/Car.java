package cst438.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;


//Car object to link to dB with its proper constructors, getters and setters. 
@Entity
@Table(name = "car")
public class Car {
	
	@Id
	private int id;
	private String model;
	private String make;
	private int year;
	private String trany;
	private double cityMPG;
	private double highwayMPG;
	private String carClass;
	private String fuelType;
	private double rentalPrice;
	private String state;
	private String city;
	private int quantity;
	private String image;
	
	public Car() {}

	public Car(int id, String model, String make, int year, String trany, double cityMPG, double highwayMPG,
			String carClass, String fuelType, double rentalPrice, String state, String city, int quantity, String image) {
		super();
		this.id = id;
		this.model = model;
		this.make = make;
		this.year = year;
		this.trany = trany;
		this.cityMPG = cityMPG;
		this.highwayMPG = highwayMPG;
		this.carClass = carClass;
		this.fuelType = fuelType;
		this.rentalPrice = rentalPrice;
		this.state = state;
		this.city = city;
		this.quantity = quantity;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTrany() {
		return trany;
	}

	public void setTrany(String trany) {
		this.trany = trany;
	}

	public double getCityMPG() {
		return cityMPG;
	}

	public void setCityMPG(double cityMPG) {
		this.cityMPG = cityMPG;
	}

	public double getHighwayMPG() {
		return highwayMPG;
	}

	public void setHighwayMPG(double highwayMPG) {
		this.highwayMPG = highwayMPG;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	

}