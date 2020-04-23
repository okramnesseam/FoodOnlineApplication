package hh.maessen.FoodOnline.FoodOnline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.maessen.FoodOnline.FoodOnline.model.User;
import hh.maessen.FoodOnline.FoodOnline.model.UserRepository;
import hh.maessen.FoodOnline.FoodOnline.model.Food;
import hh.maessen.FoodOnline.FoodOnline.model.FoodRepository;



// The main file of the application, without this the application wouldn't work.

@SpringBootApplication
public class FoodOnlineApplication {
	private static final Logger log = LoggerFactory.getLogger(FoodOnlineApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FoodOnlineApplication.class, args);
	}



	@Bean
	public CommandLineRunner demo(FoodRepository foodRepository, UserRepository userRepository) {
		return (args) -> {

			// In here I have added admin & user accounts for the program.

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			// This part is used to put the first data into the application.
			// post: id, foodname, info, price, user
						log.info("This saves few foods");
						foodRepository.save(new Food(0, "Juustohampurilais-ateria", "Sisältää ranskalaiset ja limun", 6.00, userRepository.findByUsername("daniel")));
						foodRepository.save(new Food(0, "Kerroshampurilais-ateria", "Sisältää ranskalaiset ja limun", 8.00, userRepository.findByUsername("marko")));	
						//repository.deleteAll();

						log.info("fetch all foods");
						for (Food food : foodRepository.findAll()) {
							log.info(food.toString());

						}
		};
	}} 