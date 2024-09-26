package fr.hb.icicafaitduspringavecboot.advisor;

import fr.hb.icicafaitduspringavecboot.custom_response.CustomResponse;
import fr.hb.icicafaitduspringavecboot.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityNotFoundResponse {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    CustomResponse entityNotFoundHandler(EntityNotFoundException exception){
        CustomResponse response = new CustomResponse();
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        response.setField(exception.getField());
        response.setValue(exception.getValue());
        response.setEntity(exception.getEntity());
        return response;
    }

}
