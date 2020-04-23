package hh.maessen.FoodOnline.FoodOnline.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username); // Enables searching for certain user.

} 