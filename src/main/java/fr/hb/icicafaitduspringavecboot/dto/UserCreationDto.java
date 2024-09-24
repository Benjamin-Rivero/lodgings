package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationDto{

    @NotBlank(message = "The first name must have a value")
    private String firstName;

    @NotBlank(message = "The last name must have a value")
    private String lastName;

    @NotBlank(message = "The email must have a value")
    private String email;

    @NotBlank(message = "The password must have a value")
    private String password;

    @NotBlank(message = "The role must have a value")
    private String role;

    @NotNull(message = "The birth date must be set")
    @Past(message = "The birth date must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "You have to set an address")
    private AddressDto addressDto;

}
