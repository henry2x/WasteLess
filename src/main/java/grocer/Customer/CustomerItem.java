package grocer.Customer;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import grocer.GroceryStore.Item;
import grocer.Order.Order;

@Entity
@Table(name = "CUSTOMER_ITEMS")
public class CustomerItem {
	
	
	private @Id @GeneratedValue Long id;

		

	public String item;
	
	
	
	public int quantity;
	
	public int orderId;
	
	
	public String getItem() {
		return item;
	}

	public CustomerItem() {
	}

	public void setItem(String item) {
		this.item = item;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	public CustomerItem(String i , int quantity, int orderId) {
		this.item = i;
		this.quantity = quantity;
		this.orderId = orderId;

	}


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CustomerItem))
			return false;
		CustomerItem other = (CustomerItem) obj;
		return Objects.equals(id, other.id) && Objects.equals(item, other.item) && quantity == other.quantity;
	}
	
	



}
