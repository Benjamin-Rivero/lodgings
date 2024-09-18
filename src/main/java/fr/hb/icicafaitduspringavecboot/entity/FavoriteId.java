package fr.hb.icicafaitduspringavecboot.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class FavoriteId implements Serializable {

    private String userId;

    private String lodgingId;

}
