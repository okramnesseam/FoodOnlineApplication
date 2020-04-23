package hh.maessen.FoodOnline.FoodOnline;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.maessen.FoodOnline.FoodOnline.web.FoodController;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FoodOnlineApplicationTests {

	@Autowired
	private FoodController controller;

	// Testing the program.

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}