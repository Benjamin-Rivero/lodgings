package fr.hb.icicafaitduspringavecboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {

    private String field;

    private Object value;

    private String entity;


}
