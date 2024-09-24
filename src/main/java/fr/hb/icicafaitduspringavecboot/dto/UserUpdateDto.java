package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserUpdateDto{

    @NotBlank(message = "The first name must have a value")
    private String firstName;

    @NotBlank(message = "The last name must have a value")
    private String lastName;

    @NotBlank(message = "The password must have a value")
    private String password;

}
