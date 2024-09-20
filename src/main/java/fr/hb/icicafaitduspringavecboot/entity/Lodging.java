package fr.hb.icicafaitduspringavecboot.entity;

import fr.hb.icicafaitduspringavecboot.entity.interfaces.SluggerInterface;
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
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private boolean isAccessible;

    @Column(nullable = false)
    private int nightPrice;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String slug;

    @ManyToMany
    @JoinTable(
            name = "lodging_room",
            joinColumns = @JoinColumn(name = "lodging_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "lodging")
    private List<Media> medias;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "lodging")
    private List<Review> reviews;

    @OneToMany(mappedBy = "lodging")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "lodging")
    private List<Booking> bookings;

    public void addFavorite(Favorite favorite){
        this.favorites.add(favorite);
    }

    @Override
    public String getField() {
        return name;
    }
}
