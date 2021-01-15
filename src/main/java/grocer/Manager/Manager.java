package grocer.Manager;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import grocer.GroceryStore.GroceryStore;

@Entity
@Table(name = "MANAGERS")
public class Manager {

	/*
	 * public int getStoreId() { return storeId; }
	 * 
	 * public void setStoreId(int storeId) { this.storeId = storeId; }
	 */

	private @Id @GeneratedValue(generator = "gen2")
	int id;
    private String firstName;
    private String lastName;
   // private int storeId;
    
    
    @ManyToOne(cascade = {CascadeType.ALL})
	private GroceryStore g;
	
	public GroceryStore getG() {
		return g;
	}

	public void setG(GroceryStore g) {
		this.g = g;
	}
    Manager() {
    }

    public Manager(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Manager))
			return false;
		Manager other = (Manager) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

}