package light.feather.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Supervisor {
	private String id;
	private String phone;
	private String jurisdiction;
	private String identificationNumber;
	private String firstName;
	private String lastName;

	public String toString() {
		return String.format("%s - %s, %s", jurisdiction, lastName, firstName);
	}
}
