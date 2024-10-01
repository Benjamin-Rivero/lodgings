package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LodgingDto {


    @NotBlank(message = "The name must have a value")
    private String name;

    @Positive(message = "The capacity must have a value")
    private int capacity;

    @Positive(message = "The night price must have a value")
    private int nightPrice;

    private String description;

    private boolean isAccessible;

    @NotNull(message = "You have to give an address")
    private AddressDto addressDto;

}
