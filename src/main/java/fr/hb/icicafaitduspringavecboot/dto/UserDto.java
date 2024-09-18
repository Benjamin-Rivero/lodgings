package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    private boolean isVerified;

    private LocalDate birthDate;

}
