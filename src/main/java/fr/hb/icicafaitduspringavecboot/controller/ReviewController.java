package fr.hb.icicafaitduspringavecboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.ReviewDto;
import fr.hb.icicafaitduspringavecboot.entity.Review;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewReview;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import fr.hb.icicafaitduspringavecboot.service.ReviewService;

import java.security.Principal;


@AllArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewController {


	private final ReviewService reviewService;

	@PostMapping
	@JsonView(JsonViewReview.ReviewShowView.class)
	public Review review(@Valid @RequestBody ReviewDto reviewDto, Principal principal){
		return reviewService.createReview(reviewDto,principal);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViewReview.ReviewShowView.class)
	public Review update(@Valid @RequestBody ReviewDto reviewDto, @PathVariable Long id,Principal principal){
		return reviewService.update(reviewDto,id);
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable Long id){
		return reviewService.delete(id);
	}

}