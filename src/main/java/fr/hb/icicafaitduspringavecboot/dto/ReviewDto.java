package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ReviewDto {

    @NotBlank(message = "The review must have content")
    private String content;

    @Positive(message = "The rating must be positive")
    @Max(value = 5,message = "The rating must be less than 5")
    private float rating;

}