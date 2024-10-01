package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.CreatedAtInterface;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewFavorite;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CompositeType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
//@IdClass(FavoriteId.class)
@Data
public class Favorite implements CreatedAtInterface {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @EmbeddedId
    private FavoriteId id;

    @Column(nullable = false)
    @JsonView(JsonViewFavorite.CreatedAt.class)
    private LocalDateTime createdAt;


    @MapsId("userId")
    @ManyToOne
    @JsonView(JsonViewFavorite.User.class)
//    @JoinColumn(name = "user_id")
    private User user;


    @MapsId("lodgingId")
    @ManyToOne
    @JsonView(JsonViewFavorite.Lodging.class)
//    @JoinColumn(name = "lodging_id")
    private Lodging lodging;

    /*@Id
    private String userId;

    @Id
    private String lodgingId;*/

}
