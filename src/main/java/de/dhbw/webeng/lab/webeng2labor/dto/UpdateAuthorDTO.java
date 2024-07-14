package de.dhbw.webeng.lab.webeng2labor.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateAuthorDTO {
    private String firstName;

    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    private AddressDTO address;
}
