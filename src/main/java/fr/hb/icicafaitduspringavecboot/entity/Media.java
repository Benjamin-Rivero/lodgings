package fr.hb.icicafaitduspringavecboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewMedia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(indexes = {
        @Index(columnList = "path")
})
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewMedia.Id.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViewMedia.Path.class)
    private String path;

    @Column(nullable = false)
    @JsonView(JsonViewMedia.Extension.class)
    private String extension;

//    @ManyToOne
//    private Lodging lodging;

}
