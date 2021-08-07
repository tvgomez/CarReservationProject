package cst438.controllers;


import static org.assertj.core.api.Assertions.assertThat;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import cst438.domain.Car;
import cst438.domain.CarInfo;
import cst438.repositories.CarRepository;
import cst438.repositories.ReservationRepository;
import cst438.services.CarService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CarRestControllerTest {

	@MockBean
	CarRepository carRepository;
	
	@MockBean
	ReservationRepository reservationRepository;
	
	@MockBean
	CarService carService;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<CarInfo> json;
	
	private ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

	@Before
	public void setUpEach() {
		JacksonTester.initFields(this,new ObjectMapper());
		
		 
	}
	
	@Test
	public void getCarsByCity()throws Exception{
		
		//Creating car object to fill car list then a carinfo object 

        //then writing as json string
		List <Car> care = new ArrayList<>();
		Car car_e = new Car(1,"Cruze","Chevrolet",2018,"Automatic",38.90,58.60,"Compact Class","Regular",22.25,"CA","San Antonio",1,"Compact.jpg");
		care.add(car_e);
		List<CarInfo> expected = new ArrayList<>();
		CarInfo car_1 = new CarInfo(1,"Cruze","Chevrolet",2018,"Automatic",38.90,58.60,"Compact Class","Regular",22.25,"CA","San Antonio",1,"Compact.jpg",0.02,.1,0.09,45, "String.img");
		expected.add(car_1);
		String expectedJson = mapper.writeValueAsString(expected);
		
		//Given
		given(carRepository.findByCity("San Antonio")).willReturn(care); 
		given(carService.getCarAvList("San Antonio","2021-01-02","2021-01-03")).willReturn(expected);
		
		//When
		MockHttpServletResponse response = 
				mvc.perform(get("/carrental/api/carsByCity/San Antonio/2021-01-02/2021-01-03").
		                accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse();
		
		
		//Then	
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).
		                      isEqualTo(expectedJson);
	}
	
	@Test
	public void getCarsInfo()throws Exception{
		
		//Given
		given(carService.getCarInfo(1)).willReturn(new CarInfo(1,"Cruze","Chevrolet",2020,"Automatic",38.90,58.60,"Compact Class","Regular",22.25,"CA","San Antonio",1,"Compact.jpg",0.02,.1,0.09,45, "String.img"));
		
		//When
		MockHttpServletResponse response = 
				mvc.perform(get("/carrental/api/carsByCity/details/1").accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse();
		

		//Then		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).
		                      isEqualTo(json.write(new CarInfo(1,"Cruze","Chevrolet",2020,"Automatic",38.90,58.60,"Compact Class","Regular",22.25,"CA","San Antonio",1,"Compact.jpg",0.02,.1,0.09,45, "String.img")).getJson());

	}
}
