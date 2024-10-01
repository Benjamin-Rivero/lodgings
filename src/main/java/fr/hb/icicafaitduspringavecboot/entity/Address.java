package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewAddress;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewAddress.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViewAddress.Street.class)
    private String street;

    @Column(nullable = false)
    @JsonView(JsonViewAddress.Number.class)
    private String number;

    @Column(nullable = false)
    @JsonView(JsonViewAddress.ZipCode.class)
    private String zipCode;

    @Column(nullable = false)
    @JsonView(JsonViewAddress.City.class)
    private String city;

    @Column(nullable = false)
    @JsonView(JsonViewAddress.Country.class)
    private String country;

    @Column(nullable = false)
    @JsonView(JsonViewAddress.Longitude.class)
    private float longitude;

    @Column(nullable = false)
    @JsonView(JsonViewAddress.Latitude.class)
    private float latitude;

    @JsonView(JsonViewAddress.More.class)
    private String more;

    @Column(nullable = false)
    @JsonView(JsonViewAddress.IsBilling.class)
    private Boolean isBilling;

//    @OneToOne
//    private Lodging lodging;

//    @ManyToOne
//    private User user;

}
