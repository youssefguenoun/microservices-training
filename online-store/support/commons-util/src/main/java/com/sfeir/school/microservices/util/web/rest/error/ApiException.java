package com.sfeir.school.microservices.util.web.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by youssefguenoun on 19/06/2017.
 */
@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private ErrorVM error;

    private HttpStatus httpStatusCode;

}

