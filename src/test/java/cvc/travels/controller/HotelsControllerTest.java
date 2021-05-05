package cvc.travels.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelsControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void mustReturnOkOnGettingHotelsByCity() throws Exception {
		this.mockMvc
		.perform(get("/api/hotels/city/7110"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void mustReturnOkOnGettingHotelById() throws Exception {
		this.mockMvc
		.perform(get("/api/hotels/1"))
		.andExpect(status().isOk());
	}
}
