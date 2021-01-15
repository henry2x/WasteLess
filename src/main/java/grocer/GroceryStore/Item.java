package grocer.GroceryStore;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "GROCERY_STORE_ITEMS")
public class Item {

	private @Id @GeneratedValue Long id;
	
	@Column(name = "item_name")
	public String name;
	
	public GroceryStore getG() {
		return g;
	}

	public void setG(GroceryStore g) {
		this.g = g;
	}

	@Column(name = "category_number")
	public int category;
	
	@Column(name = "expiry_date")
	public String expiry_date;
	
	@ManyToOne
	private GroceryStore g;
	
	
	
	public Item() {
	}
	
	
	public Item(String name, int category, String expiryDate) {
		this.name = name;
		this.category = category;
		this.expiry_date = expiryDate;
	}
	
	public Long getId() {
		return id;
	}
	

    public void setId(Long id) {
        this.id = id;
    }

	public String getExpiryDate() {
		return expiry_date;
	}
	
	public void setExpiryDate(String e) {
		expiry_date = e;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, expiry_date, g, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		return category == other.category && Objects.equals(expiry_date, other.expiry_date)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", category=" + category + ", expiryDate=" + expiry_date
				 + "]";
	}
	
	
	
}
