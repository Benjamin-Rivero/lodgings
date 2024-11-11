package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    @NotBlank(message = "You must specify a room type")
    private String type;

}
