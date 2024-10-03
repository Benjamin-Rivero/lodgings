package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.ReviewDto;
import fr.hb.icicafaitduspringavecboot.entity.Review;
import fr.hb.icicafaitduspringavecboot.repository.ReviewRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ReviewService{

    private final ReviewRepository reviewRepository;
    private final LodgingService lodgingService;
    private final UserService userService;

    public Review create(ReviewDto object) {
        return toEntity(object);
    }

    private Review toEntity(ReviewDto object) {
        Review review = new Review();
        review.setCreatedAt(LocalDateTime.now());
        review.setContent(object.getContent());
        review.setRating(object.getRating());
        review.setLodging(lodgingService.findById(object.getLodgingId()));
        return review;
    }

    public Review update(ReviewDto object, Long id) {
        Review review = findById(id);
        if(review != null) {
            review.setRating(object.getRating());
            review.setContent(object.getContent());
            reviewRepository.saveAndFlush(review);
            return review;
        }
        return null;
    }

    public boolean delete(Long id) {
        try {
            Review review = findById(id);
            review.setContent("Commentaire supprimé");
            review.setRating(null);
            reviewRepository.saveAndFlush(review);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Review createReview(ReviewDto reviewDto, Principal principal) {
        Review review = create(reviewDto);
        review.setUser(userService.findByEmail(principal.getName()));
        return reviewRepository.saveAndFlush(review);
    }
}
