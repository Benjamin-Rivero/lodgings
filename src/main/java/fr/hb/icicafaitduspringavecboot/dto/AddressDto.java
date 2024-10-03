package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    @NotBlank(message = "The street must be set")
    private String street;

    @NotBlank(message = "The street number must be set")
    private String number;

    @NotBlank(message = "The zipCode must be set")
    private String zipCode;

    @NotBlank(message = "The city must be set")
    private String city;

    @NotBlank(message = "The country must be set")
    private String country;

    private String longitude;

    private String latitude;

    private String more;

    private Boolean isBilling;

}
