package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewRoom;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewRoom.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViewRoom.TranslationKey.class)
    private String translationKey;

    @Column(nullable = false)
    @JsonView(JsonViewRoom.Type.class)
    private String type;

    /*@ManyToMany(mappedBy = "rooms")
    private List<Lodging> lodgings = new ArrayList<>();*/

}
