package grocer.GroceryStore;

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
import javax.persistence.Table;
import grocer.GroceryStore.*;

@Entity
@Table(name = "INVENTORIES")
@Access(value=AccessType.FIELD)
public class Inventory {

	private @Id @GeneratedValue Long id;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	public Item item;
	
	
	public int quantity;
	

	@OneToOne(cascade = {CascadeType.MERGE})
	private GroceryStore g;
	
	public GroceryStore getG() {
		return g;
	}

	public void setG(GroceryStore g) {
		this.g = g;
	}

	public Item getItem() {
		return item;
	}

	public Inventory() {
	}

	public void setItem(Item item) {
		this.item = item;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	public Inventory(Item i , int quantity) {
		this.item = i;
		this.quantity = quantity;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
