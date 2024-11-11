package fr.hb.icicafaitduspringavecboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExpiredCodeException extends RuntimeException{

    public ExpiredCodeException(String message) {
        super(message);
    }

}
