package fr.hb.icicafaitduspringavecboot.controller;

import fr.hb.icicafaitduspringavecboot.dto.LodgingDto;
import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import fr.hb.icicafaitduspringavecboot.repository.LodgingRepository;
import fr.hb.icicafaitduspringavecboot.service.LodgingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/lodging")
@RestController
@AllArgsConstructor
public class LodgingController {

    private final LodgingService lodgingService;

    @PostMapping
    public Lodging createLodging(@Valid @RequestBody LodgingDto lodgingDto){
        return lodgingService.create(lodgingDto);
    }

}
