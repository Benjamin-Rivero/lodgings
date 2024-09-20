package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.entity.Review;
import fr.hb.icicafaitduspringavecboot.repository.ReviewRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewService implements ServiceInterface<Review,Long> {

    private final ReviewRepository reviewRepository;

    @Override
    public Review create(Review object) {
        return reviewRepository.saveAndFlush(object);
    }

    @Override
    public Review update(Review object, Long id) {
        object.setId(id);
        reviewRepository.flush();
        return object;
    }

    @Override
    public void delete(Review object) {
        if(object!=null) reviewRepository.delete(object);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
