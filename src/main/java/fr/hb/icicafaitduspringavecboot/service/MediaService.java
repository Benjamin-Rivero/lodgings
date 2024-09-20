package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.entity.Media;
import fr.hb.icicafaitduspringavecboot.repository.MediaRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MediaService implements ServiceInterface<Media,Long> {

    private final MediaRepository mediaRepository;

    @Override
    public Media create(Media object) {
        return mediaRepository.saveAndFlush(object);
    }

    @Override
    public Media update(Media object, Long id) {
        object.setId(id);
        mediaRepository.flush();
        return object;
    }

    @Override
    public void delete(Media object) {
        if(object!=null) mediaRepository.delete(object);
    }

    @Override
    public Media findById(Long id) {
        return mediaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
