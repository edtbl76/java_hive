package example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer1111 {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;
	private String firstName;
	private String lastName;

	protected Customer1111() {}

	public Customer1111(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer1111[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

}
