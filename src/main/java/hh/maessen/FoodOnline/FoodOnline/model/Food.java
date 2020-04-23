package hh.maessen.FoodOnline.FoodOnline.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Food {

	// These are the basic attributes for the program.

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String foodname;
	private String info;
	private double price;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    private User user;

	public Food() {
		super();
	}

	public Food(long id, String foodname, String info, double price, User user) {
		super();
		this.id = id;
		this.foodname = foodname;
		this.info = info;
		this.price = price;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", foodname=" + foodname + ", info=" + info + ", price=" + price + ", user=" + user
				+ "]";
	}

} 