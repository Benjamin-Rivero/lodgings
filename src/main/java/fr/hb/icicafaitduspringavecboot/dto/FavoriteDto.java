package fr.hb.icicafaitduspringavecboot.dto;

import fr.hb.icicafaitduspringavecboot.entity.FavoriteId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FavoriteDto {

    @NotNull(message = "You have to set ids")
    private FavoriteId ids;

}
