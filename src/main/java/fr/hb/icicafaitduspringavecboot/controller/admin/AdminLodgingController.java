package fr.hb.icicafaitduspringavecboot.controller.admin;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.LodgingDto;
import fr.hb.icicafaitduspringavecboot.dto.MediaDto;
import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewLodging;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.hb.icicafaitduspringavecboot.service.LodgingService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/admin/lodging")
public class AdminLodgingController {


    private final LodgingService lodgingService;

    @PostMapping
    @JsonView(JsonViewLodging.LodgingShowView.class)
    public Lodging createLodging(@Valid @RequestBody LodgingDto lodgingDto){
        return lodgingService.create(lodgingDto);
    }

    @PostMapping("/addmedia")
    @JsonView(JsonViewLodging.LodgingMinimalView.class)
    public Lodging addMedia(@RequestBody MediaDto mediaDto){
        return lodgingService.addMedia(mediaDto);
    }

}