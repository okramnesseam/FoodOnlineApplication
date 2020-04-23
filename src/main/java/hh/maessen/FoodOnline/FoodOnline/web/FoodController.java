package hh.maessen.FoodOnline.FoodOnline.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.maessen.FoodOnline.FoodOnline.model.Food;
import hh.maessen.FoodOnline.FoodOnline.model.FoodRepository;

@Controller
public class FoodController {
	@Autowired
	private FoodRepository repository;

	// Show all foods
    @RequestMapping(value="/foodlist")
    public String foodlist(Model model) {	
        model.addAttribute("foods", repository.findAll());
        return "foodlist";
    }

	// RESTful service to get all foods !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @RequestMapping(value="/foods", method = RequestMethod.GET)
    public @ResponseBody List<Food> foodListRest() {	
        return (List<Food>) repository.findAll();
    }    

	// RESTful service to get food by id !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @RequestMapping(value="/foods/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Food> findFoodRest(@PathVariable("id") Long foodId) {	
    	return repository.findById(foodId);
    }       

    // Adds more foods to the list
    @RequestMapping(value = "/add")
    public String addFood(Model model){
    	model.addAttribute("food", new Food());
        return "addfood";
    }  

    // Save new food
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Food food){
        repository.save(food);
        return "redirect:foodlist";
    }  

    // Delete food
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteFood(@PathVariable("id") Long foodId, Model model) {
    	repository.deleteById(foodId);
        return "redirect:../foodlist";
    }    

    // Edit food
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modifyFood(@PathVariable("id") Long foodId, Model model) {
    	Optional<Food> food = repository.findById(foodId);
    	model.addAttribute("food", food);
    	System.out.println("GGGGGGG");
        return "modifyfood";
    }    

    // Show all foods
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	}

    // Logout
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
     }

    // Places your order.
    @RequestMapping(value="/ordercompleted", method = RequestMethod.GET)
    public String orderFood (HttpServletRequest request, HttpServletResponse response) {
		return "ordercompleted";

    }
}
