package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.MediaDto;
import fr.hb.icicafaitduspringavecboot.entity.Media;
import fr.hb.icicafaitduspringavecboot.repository.MediaRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MediaService{

    private final MediaRepository mediaRepository;

    public Media create(MediaDto object) {
        return mediaRepository.saveAndFlush(toEntity(object));
    }

    private Media toEntity(MediaDto object) {
        Media media = new Media();
        String[] splitPath = object.getPathWithExtension().split("\\.",2);
        media.setExtension(splitPath[1]);
        media.setPath(splitPath[0]);
        return media;
    }

    public Media update(MediaDto object, Long id) {
        Media media = toEntity(object);
        media.setId(id);
        mediaRepository.saveAndFlush(media);
        return media;
    }

    public void delete(Media object) {
        if(object!=null) mediaRepository.delete(object);
    }

    public Media findById(Long id) {
        return mediaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
