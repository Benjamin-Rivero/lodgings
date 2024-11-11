package fr.hb.icicafaitduspringavecboot.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteId implements Serializable {

    @NotBlank(message = "You need to specify a user")
    private String userId;

    @NotBlank(message = "You need to specify a lodging")
    private String lodgingId;

}
