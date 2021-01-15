package grocer.Order;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import grocer.Status;
import grocer.GroceryStore.GroceryStore;
import grocer.GroceryStore.Item;

@Entity
@Table(name = "CUSTOMERS_ORDERS")
@Access(value = AccessType.FIELD)
public class Order {

	
	@Id 
	@GeneratedValue(generator = "gen")
	public Long id;

	@Column(name = "Foodbank_Name")
	public String foodbank_name;

	@Column(name = "Order_Status")
	public Status status;

	/*
	 * private ArrayList<String> items; private ArrayList<Integer> quantities;
	 */
	public String pickup_time;
	public String pickup_date;
	@ManyToOne
	private GroceryStore g;

	public String getFoodbank_name() {
		return foodbank_name;
	}

	public void setFoodbank_name(String foodbank_name) {
		this.foodbank_name = foodbank_name;
	}

	Order() {
	}

	/*
	 * Order(String description, Status status, ArrayList<String> items,
	 * ArrayList<Integer> quantities,Date pickupTime) { this.description =
	 * description; this.status = status; this.items = items; this.quantities =
	 * quantities; this.pickup_time = pickupTime; }
	 */

	public Long getId() {
		return this.id;
	}

	public GroceryStore getG() {
		return g;
	}

	public void setG(GroceryStore g) {
		this.g = g;
	}

	public Order(String name, Status status, String pickup_time, String pickup_date) {
		super();
		this.foodbank_name = name;
		this.status = status;
		this.pickup_time = pickup_time;
		this.pickup_date = pickup_date;
	}


	/*
	 * public ArrayList<String> getItems() { return items; }
	 * 
	 * public void setItems(ArrayList<String> items) { this.items = items; }
	 * 
	 * public ArrayList<Integer> getQuantities() { return quantities; }
	 */
	/*
	 * public void setQuantities(ArrayList<Integer> quantities) { this.quantities =
	 * quantities; }
	 */
	public String getPickup_time() {
		return pickup_time;
	}
	
	
	public void acceptOrder() {
		this.status = Status.ACCEPTED;
	}
	
	public void cancelOrder() {
		this.status = Status.CANCELLED;
	}
	
	public void setPickup_time(String pickup_time) {
		this.pickup_time = pickup_time;
	}

	public String getDescription() {
		return this.foodbank_name;
	}

	public Status getStatus() {
		return this.status;
	}

	public String getPickupTime() {
		return this.pickup_time;

	}

	public void setPickupTime(String d) {
		this.pickup_time = d;
	}

	public String getPickup_date() {
		return pickup_date;
	}

	public void setPickup_date(String pickup_date) {
		this.pickup_date = pickup_date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.foodbank_name = description;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void completeOrder() {
		this.status = Status.COMPLETED;
	}

	@Override
	public int hashCode() {
		return Objects.hash(foodbank_name, id, pickup_time, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		return Objects.equals(foodbank_name, other.foodbank_name) && Objects.equals(id, other.id)
				&& Objects.equals(pickup_time, other.pickup_time) && status == other.status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", foodbank name=" + foodbank_name + ", status=" + status + ", pickup_time="
				+ pickup_time + "]";
	}

	/*
	 * @Override public int hashCode() { return Objects.hash(description, id,
	 * pickup_time, status); }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (!(obj instanceof Order)) return false; Order other = (Order) obj; return
	 * Objects.equals(description, other.description) && Objects.equals(id,
	 * other.id) && Objects.equals(items, other.items) &&
	 * Objects.equals(pickup_time, other.pickup_time) && status == other.status; }
	 */

	/*
	 * @Override public String toString() { return "Order{" + "id=" + this.id +
	 * ", description='" + this.description + '\'' + ", status=" + this.status +
	 * ", status=" + this.items.toString() + ", pickupTime=" +
	 * this.pickup_time.toString() +'}'; }
	 */
}