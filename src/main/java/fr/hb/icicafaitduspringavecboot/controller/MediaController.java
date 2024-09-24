package fr.hb.icicafaitduspringavecboot.controller;

import fr.hb.icicafaitduspringavecboot.dto.MediaDto;
import fr.hb.icicafaitduspringavecboot.entity.Media;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.hb.icicafaitduspringavecboot.service.MediaService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    public Media create(@Valid @RequestBody MediaDto mediaDto){
        return mediaService.create(mediaDto);
    }

}