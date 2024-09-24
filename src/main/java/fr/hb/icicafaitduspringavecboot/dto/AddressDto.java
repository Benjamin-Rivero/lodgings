package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

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

    @Positive(message = "The longitude must be positive")
    private Float longitude;

    @Positive(message = "The latitude must be positive")
    private Float latitude;

    private String more;

    private Boolean isBilling;

}
