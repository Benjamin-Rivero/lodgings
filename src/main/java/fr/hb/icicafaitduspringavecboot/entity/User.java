package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.CreatedAtInterface;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.SluggerInterface;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewUser;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User implements CreatedAtInterface, SluggerInterface, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewUser.Id.class)
    private String id;

    @JsonView(JsonViewUser.FirstName.class)
    private String firstName;

    @JsonView(JsonViewUser.LastName.class)
    private String lastName;

    @Column(nullable = false)
    @JsonView(JsonViewUser.Email.class)
    private String email;

    @JsonView(JsonViewUser.Password.class)
    private String password;

    @Column(nullable = false)
    @JsonView(JsonViewUser.Roles.class)
    private String roles;

    @JsonView(JsonViewUser.Birthdate.class)
    private LocalDate birthDate;

    @Column(nullable = false)
    @JsonView(JsonViewUser.CreatedAt.class)
    private LocalDateTime createdAt;

    @JsonView(JsonViewUser.Phone.class)
    private String phone;

    @JsonView(JsonViewUser.Photo.class)
    private String photo;

    @JsonView(JsonViewUser.Slug.class)
    private String slug;

    @JsonView(JsonViewUser.ActivationToken.class)
    private String activationToken;

    @JsonView(JsonViewUser.ActivationTokenSentAt.class)
    private LocalDateTime activationTokenSentAt;

    public boolean isActive() {
        return activationToken == null;
    }

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViewUser.Reviews.class)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViewUser.Favorites.class)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViewUser.Bookings.class)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "userId")
    @JsonView(JsonViewUser.Addresses.class)
    private List<Address> addresses = new ArrayList<>();

    public void addFavorite(Favorite favorite){
        this.favorites.add(favorite);
    }

    @Override
    public String getField() {
        return firstName+"-"+lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @JsonIgnore
    public String getFullName() {
        return firstName+" "+lastName;
    }
}
