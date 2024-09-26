package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.CreatedAtInterface;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViews;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review implements CreatedAtInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.ReviewListView.class)
    private String content;

    @Column(nullable = false)
    @JsonView(JsonViews.ReviewListView.class)
    private float rating;

    @Column(nullable = false)
    @JsonView(JsonViews.ReviewListView.class)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;

}
