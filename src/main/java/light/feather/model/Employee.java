package light.feather.model;

import lombok.Data;

@Data
public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String supervisor;
}
