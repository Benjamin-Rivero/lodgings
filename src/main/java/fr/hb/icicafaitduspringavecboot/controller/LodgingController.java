package fr.hb.icicafaitduspringavecboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.LodgingDto;
import fr.hb.icicafaitduspringavecboot.dto.MediaDto;
import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewLodging;
import fr.hb.icicafaitduspringavecboot.service.LodgingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/lodging")
@RestController
@AllArgsConstructor
public class LodgingController {

    private final LodgingService lodgingService;



    @GetMapping
    @JsonView(JsonViewLodging.LodgingMinimalView.class)
    public List<Lodging> list(){
        return lodgingService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViewLodging.LodgingShowView.class)
    public Lodging show(@PathVariable String id){
        return lodgingService.findById(id);
    }



}
