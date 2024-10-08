package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDto {

    @NotBlank(message = "You have to set the lodging id")
    private String lodgingId;

    @NotNull(message = "You have to set a starting date")
    @Future(message = "Your reservation can't be made in the past")
    private LocalDateTime startedAt;

//    @NotNull(message = "You have to set an ending date")
//    private LocalDateTime finishedAt;

    @Positive(message = "You have to specify a positive number of days")
    private int numberOfDays;

    @Positive(message = "The quantity must be positive")
    private int quantity;

}
