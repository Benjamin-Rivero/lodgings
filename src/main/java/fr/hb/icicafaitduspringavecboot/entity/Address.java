package fr.hb.icicafaitduspringavecboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private float longitude;

    @Column(nullable = false)
    private float latitude;

    private String more;

    @Column(nullable = false)
    private boolean isBilling;

//    @OneToOne
//    private Lodging lodging;

//    @ManyToOne
//    private User user;

}
