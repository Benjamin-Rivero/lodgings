package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.CreatedAtInterface;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.SluggerInterface;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViews;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User implements CreatedAtInterface, SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @JsonView(JsonViews.UserListView.class)
    private String firstName;

    @Column(nullable = false)
    @JsonView(JsonViews.UserListView.class)
    private String lastName;

    @Column(nullable = false)
    @JsonView(JsonViews.UserListView.class)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean isVerified;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShowView.class)
    private LocalDate birthDate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private String phone;

    @JsonView(JsonViews.UserShowView.class)
    private String photo;

    @JsonView(JsonViews.UserListView.class)
    private String slug;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserShowView.class)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Address> addresses = new ArrayList<>();

    public void addFavorite(Favorite favorite){
        this.favorites.add(favorite);
    }

    @Override
    public String getField() {
        return firstName+"-"+lastName;
    }
}
