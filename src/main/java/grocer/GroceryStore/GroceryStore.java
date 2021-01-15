package grocer.GroceryStore;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GROCERY_STORES")
@Access(value=AccessType.FIELD)
public class GroceryStore {

	private @Id @GeneratedValue Long id;
	
	
	@Column(name = "Grocery_Store_Name")
	public String name;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	public Address address;
	
	
	
	public GroceryStore() {		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	
	
	public GroceryStore(Address address, String name) {
		this.address = address;
		this.name = name;
		
		
	}
	

	@JsonIgnore
	public Address getAddress() {
		return this.address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

	
	

	/*

	public void setManagers(List<Manager> managers) {
		managers = new ArrayList<Manager>();
		for(Manager m : managers) 
		this.managers.add(m);
	}
	public List<Manager> getManagers() {
		return managers;
	}
	
	public void addManager(Manager m) {
		this.managers.add(m);
	}
	
	public void removeManager(Manager m) {
		this.managers.remove(m);
	}
	
	public void addItem(Item i, int quantity) {
		this.inventory.addItem(i, quantity);
	}
	
	public void removeItem(Item i) {
		this.inventory.removeItem(i);
	}
	
	
	public List<Order> getOrders() {
		return orders;
	}*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id2) {
		this.id = id2;
		
	}

	/*
	public void setManagers(ArrayList<Manager> managers) {
		this.managers = managers;
	}


	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	*/
	@Override
	public String toString() {
		return "GroceryStore [id=" + id + ", name=" + name + ", address=" + address + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(address, id, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GroceryStore))
			return false;
		GroceryStore other = (GroceryStore) obj;
		return Objects.equals(address, other.address) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}
	
	
}
