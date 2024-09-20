package fr.hb.icicafaitduspringavecboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookingDto {

    @NotBlank(message = "You have to set the user id")
    private String userId;

    @NotBlank(message = "You have to set the lodging id")
    private String lodgingId;

    @NotBlank(message = "The number must have a value")
    private String number;

    @NotNull(message = "You have to set a starting date")
    private LocalDateTime startedAt;

    @NotNull(message = "You have to set an ending date")
    private LocalDateTime finishedAt;

    @Positive(message = "The quantity must be positive")
    private int quantity;

}
