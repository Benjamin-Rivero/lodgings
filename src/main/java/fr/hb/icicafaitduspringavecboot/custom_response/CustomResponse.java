package fr.hb.icicafaitduspringavecboot.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonView()
public class CustomResponse {

    private int status;

    private String field;

    private Object value;

    private String entity;

}
