package cst438.domain;


//Car info object to modify incoming car information. 
//Adds tax fees as well as imageURL to send to API to display image in site. 
//Has its proper constructors, getters, setters, equals and toString methods.
public class CarInfo {
	
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
	private double countyTax;
	private double govFee;
	private double salesTax;
	private double total;
	private String imageURL;
	
	public CarInfo() {}

	public CarInfo(int id, String model, String make, int year, String trany, double cityMPG, double highwayMPG,
			String carClass, String fuelType, double rentalPrice, String state, String city, int quantity, String image,
			double countyTax, double govFee, double salesTax, double total, String imageURL) {
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
		this.countyTax = countyTax;
		this.govFee = govFee;
		this.salesTax = salesTax;
		this.total = total;
		this.imageURL = imageURL;
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

	public double getCountyTax() {
		return countyTax;
	}

	public void setCountyTax(double countyTax) {
		this.countyTax = countyTax;
	}

	public double getGovFee() {
		return govFee;
	}

	public void setGovFee(double govFee) {
		this.govFee = govFee;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "CarInfo [id=" + id + ", model=" + model + ", make=" + make + ", year=" + year + ", trany=" + trany
				+ ", cityMPG=" + cityMPG + ", highwayMPG=" + highwayMPG + ", carClass=" + carClass + ", fuelType="
				+ fuelType + ", rentalPrice=" + rentalPrice + ", state=" + state + ", city=" + city + ", quantity="
				+ quantity + ", image=" + image + ", countyTax=" + countyTax + ", govFee=" + govFee + ", salesTax="
				+ salesTax + ", total=" + total + ", imageURL=" + imageURL + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarInfo other = (CarInfo) obj;
		if (carClass == null) {
			if (other.carClass != null)
				return false;
		} else if (!carClass.equals(other.carClass))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (Double.doubleToLongBits(cityMPG) != Double.doubleToLongBits(other.cityMPG))
			return false;
		if (Double.doubleToLongBits(countyTax) != Double.doubleToLongBits(other.countyTax))
			return false;
		if (fuelType == null) {
			if (other.fuelType != null)
				return false;
		} else if (!fuelType.equals(other.fuelType))
			return false;
		if (Double.doubleToLongBits(govFee) != Double.doubleToLongBits(other.govFee))
			return false;
		if (Double.doubleToLongBits(highwayMPG) != Double.doubleToLongBits(other.highwayMPG))
			return false;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (imageURL == null) {
			if (other.imageURL != null)
				return false;
		} else if (!imageURL.equals(other.imageURL))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(rentalPrice) != Double.doubleToLongBits(other.rentalPrice))
			return false;
		if (Double.doubleToLongBits(salesTax) != Double.doubleToLongBits(other.salesTax))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		if (trany == null) {
			if (other.trany != null)
				return false;
		} else if (!trany.equals(other.trany))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	
	
}
