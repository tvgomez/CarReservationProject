package cst438.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import cst438.domain.Reservation;
import cst438.repositories.CarRepository;
import cst438.repositories.ReservationRepository;
import cst438.services.CarService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ReservationRestControllerTest {
	
	// The testing suite includes MockBean, JacksonTester
	@MockBean
	CarRepository carRepository;
	
	@MockBean
	ReservationRepository reservationRepository;
	
	@MockBean
	CarService carService;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<Reservation> json;
	
	private ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();


	
	@Before
	public void setUpEach() {
		JacksonTester.initFields(this,new ObjectMapper());
		
		
	}

	
	// These tests check our reservation controller
	@Test
	public void getCarreserve()throws Exception{
		
		// Our list objects created
		List<Reservation> reserved = new ArrayList<>();
		
		Reservation res_1 = new Reservation(1, "avillagomez@csumb.edu",1,"1-02-2021","1-03-2021");
		
		//Added
		reserved.add(res_1);
		
		given(reservationRepository.findById(1)).willReturn(reserved);
		MockHttpServletResponse response = 
				mvc.perform(get("/carrental/api/reservations/details/1").accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse();
		
		
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).
		                      isEqualTo(json.write(res_1).getJson());
	}
	@Test
	public void getReservationEmail()throws Exception{
		
		List<Reservation> reserved = new ArrayList<>();
		
		Reservation res_2 = new Reservation(1,"avillagomez@csumb.edu",1,"1-02-2021","1-03-2021");
		
		reserved.add(res_2);
		
		// Json that is expected turned to string
		String expectedJson = mapper.writeValueAsString(reserved);
		
		
	
		given(reservationRepository.findByEmail("avillagomez@csumb.edu")).willReturn(reserved);
		
		//Then
		MockHttpServletResponse response = 
				mvc.perform(get("/carrental/api/reservations/avillagomez@csumb.edu").accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse();
		
		//Then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).
		                      isEqualTo(expectedJson);
	
		
	}
	
}
