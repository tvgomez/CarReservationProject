package cst438.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Id;

@Entity
@Table(name = "reservations")
public class Reservation {
	
	//Reservation object to link to dB with its proper constructors, getters and setters. 
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Size(min = 3, max =45)
	private String email;
	
	private int car_id;
	
	private String date_start;
	private String date_end;
	
	public Reservation() {}

	public Reservation(int id, @NotNull @Size(min = 3, max = 45) String email, int car_id, String date_start,
			String date_end) {
		super();
		this.id = id;
		this.email = email;
		this.car_id = car_id;
		this.date_start = date_start;
		this.date_end = date_end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public String getDate_start() {
		return date_start;
	}

	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}


	

}
