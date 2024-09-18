package fr.hb.icicafaitduspringavecboot.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String translationKey;

    @Column(nullable = false)
    private String type;

    @ManyToMany(mappedBy = "rooms")
    private List<Lodging> lodgings = new ArrayList<>();

}
