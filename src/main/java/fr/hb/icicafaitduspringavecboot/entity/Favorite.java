package fr.hb.icicafaitduspringavecboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
//@IdClass(FavoriteId.class)
@Data
public class Favorite {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @EmbeddedId
    private FavoriteId id;

    @Column(nullable = false)
    private LocalDateTime createdAt;


    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @MapsId("lodgingId")
    @ManyToOne
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;

    /*@Id
    private String userId;

    @Id
    private String lodgingId;*/

}
