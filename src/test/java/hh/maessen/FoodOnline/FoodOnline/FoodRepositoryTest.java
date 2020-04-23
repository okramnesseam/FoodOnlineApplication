package hh.maessen.FoodOnline.FoodOnline;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import hh.maessen.FoodOnline.FoodOnline.model.Food;
import hh.maessen.FoodOnline.FoodOnline.model.FoodRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FoodRepositoryTest {

	@Autowired
	private FoodRepository repository;

	// Testing if a certain food is found from the list

	@Test
	public void findbyFoodnameShouldReturnFood() {
		List<Food> foods = repository.findByFoodname("Juustohampurilainen");

		assertThat(foods).isNotNull();
		assertThat(foods.get(0).getFoodname()).isEqualTo("Juustohampurilainen");
	}

	// Testing creating a new food to the list

	@Test
	public void createNewFood() {
		Food food = new Food(0, "Kerroshampurilainen", "Ei sovi keliaakikoille", 5.50, null);
		repository.save(food);
		assertThat(food.getId()).isNotNull();
	}

}