package de.dhbw.webeng.lab.webeng2labor.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO address;
}
