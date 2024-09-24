package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.ReviewDto;
import fr.hb.icicafaitduspringavecboot.entity.Review;
import fr.hb.icicafaitduspringavecboot.repository.ReviewRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewService implements ServiceInterface<Review,Long, ReviewDto,ReviewDto> {

    private final ReviewRepository reviewRepository;
    private final LodgingService lodgingService;
    private final UserService userService;

    @Override
    public Review create(ReviewDto object) {
        return reviewRepository.saveAndFlush(toEntity(object));
    }

    private Review toEntity(ReviewDto object) {
        Review review = new Review();
        review.setContent(object.getContent());
        review.setRating(object.getRating());
        review.setLodging(lodgingService.findById(object.getLodgingId()));
        review.setUser(userService.findById(object.getUserId()));
        return review;
    }

    @Override
    public Review update(ReviewDto object, Long id) {
        Review review = toEntity(object);
        review.setId(id);
        reviewRepository.saveAndFlush(review);
        return review;
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
