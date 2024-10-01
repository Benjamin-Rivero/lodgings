package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.CreatedAtInterface;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewBooking;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking implements CreatedAtInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewBooking.Id.class)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(JsonViewBooking.User.class)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lodging_id")
    @JsonView(JsonViewBooking.Lodging.class)
    private Lodging lodging;

    @Column(nullable = false)
    @JsonView(JsonViewBooking.Number.class)
    private String number;

    @Column(nullable = false)
    @JsonView(JsonViewBooking.CreatedAt.class)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @JsonView(JsonViewBooking.StartedAt.class)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    @JsonView(JsonViewBooking.FinishedAt.class)
    private LocalDateTime finishedAt;

    @Column(nullable = false)
    @JsonView(JsonViewBooking.Quantity.class)
    private int quantity;



}
