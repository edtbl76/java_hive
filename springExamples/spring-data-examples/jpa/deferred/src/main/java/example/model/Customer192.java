package example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer192 {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;
	private String firstName;
	private String lastName;

	protected Customer192() {}

	public Customer192(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer192[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

}
