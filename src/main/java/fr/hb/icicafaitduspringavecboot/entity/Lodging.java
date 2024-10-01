package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.entity.interfaces.SluggerInterface;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewLodging;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(columnList = "slug", unique = true)})
@Data
public class Lodging implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewLodging.Id.class)
    private String id;

    @Column(nullable = false)
    @JsonView(JsonViewLodging.Name.class)
    private String name;

    @Column(nullable = false)
    @JsonView(JsonViewLodging.Capacity.class)
    private int capacity;

    @Column(nullable = false)
    @JsonView(JsonViewLodging.IsAccessible.class)
    private boolean isAccessible;

    @Column(nullable = false)
    @JsonView(JsonViewLodging.NightPrice.class)
    private int nightPrice;

    @Column(columnDefinition = "TEXT")
    @JsonView(JsonViewLodging.Description.class)
    private String description;

    @Column(nullable = false)
    @JsonView(JsonViewLodging.Slug.class)
    private String slug;

    @ManyToMany
    @JoinTable(
            name = "lodgingRoom",
            joinColumns = @JoinColumn(name = "lodgingId"),
            inverseJoinColumns = @JoinColumn(name = "roomId")
    )
    @JsonView(JsonViewLodging.Rooms.class)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "lodgingId")
    @JsonView(JsonViewLodging.Medias.class)
    private List<Media> medias;

    @OneToOne
    @JsonView(JsonViewLodging.Address.class)
    private Address address;

    @OneToMany(mappedBy = "lodging")
    @JsonView(JsonViewLodging.Reviews.class)
    private List<Review> reviews;

    @OneToMany(mappedBy = "lodging")
    @JsonView(JsonViewLodging.Favorites.class)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "lodging")
    @JsonView(JsonViewLodging.Bookings.class)
    private List<Booking> bookings;

    public void addFavorite(Favorite favorite){
        this.favorites.add(favorite);
    }

    @Override
    public String getField() {
        return name;
    }
}
