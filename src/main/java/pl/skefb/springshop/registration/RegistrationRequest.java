package pl.skefb.springshop.registration;

import lombok.*;

@Data
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String telephone;
}
