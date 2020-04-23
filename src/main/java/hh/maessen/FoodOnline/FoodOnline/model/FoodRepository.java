package hh.maessen.FoodOnline.FoodOnline.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// This is one of the central files storage location.

public interface FoodRepository extends CrudRepository<Food, Long> {

	List<Food> findByFoodname(String foodname); // Enables searching for a certain food from foodlist
}