package fr.hb.icicafaitduspringavecboot.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteId implements Serializable {

    private String userId;

    private String lodgingId;

}
