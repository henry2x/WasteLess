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

@Entity
@Table(name = "ADDRESSES")
public class Address {



	@Id
	@GeneratedValue
	private long id;

	@Column(name = "Address_Line")
	private String addressLine;
	
	@Column(name = "Postal_Code")
	private String zipcode;
	
	@Column(name = "City")
	private String city;

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	

	public Address() {
	}

	public Address(String addressLine, String zipcode, String city) {
		this.addressLine = addressLine;
		this.zipcode = zipcode;
		this.city = city;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine = addressLine1;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	



}
