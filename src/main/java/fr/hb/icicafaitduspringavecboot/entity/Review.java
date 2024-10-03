package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.CreatedAtInterface;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewReview;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review implements CreatedAtInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewReview.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViewReview.Content.class)
    private String content;

    @JsonView(JsonViewReview.Rating.class)
    private Float rating;

    @Column(nullable = false)
    @JsonView(JsonViewReview.CreatedAt.class)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(JsonViewReview.User.class)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lodging_id")
    @JsonView(JsonViewReview.Lodging.class)
    private Lodging lodging;

}
