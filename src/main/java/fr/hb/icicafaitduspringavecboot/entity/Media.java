package fr.hb.icicafaitduspringavecboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(columnList = "path")
})
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String path;

    @Column(nullable = false)
    private String extension;

//    @ManyToOne
//    private Lodging lodging;

}
