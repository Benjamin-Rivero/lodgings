package fr.hb.icicafaitduspringavecboot.dto;

import lombok.Data;

@Data
public class LodgingDto {


    private String name;

    private int capacity;

    private int nightPrice;

    private String description;

    private String slug;

    private Long addressId;

}
