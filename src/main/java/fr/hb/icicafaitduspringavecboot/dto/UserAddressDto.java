package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserAddressDto {

    @NotBlank(message = "The street must have a value")
    private String street;

    @NotBlank(message = "The street number must have a value")
    private String number;

    @NotBlank(message = "The zipCode must have a value")
    private String zipCode;

    @NotBlank(message = "The city must have a value")
    private String city;

    @NotBlank(message = "The country must have a value")
    private String country;

    private String more;

    private boolean isBilling;

}
