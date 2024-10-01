package fr.hb.icicafaitduspringavecboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AlreadyActiveException extends RuntimeException {

    public AlreadyActiveException(String message) {
        super(message);
    }

}
