package com.sfeir.school.microservices.util.web.rest.error;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by youssefguenoun on 21/06/2017.
 */

@Component
public class ApiClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private static final Logger logger = LoggerFactory.getLogger(ApiClientErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorVM errorVM = new ErrorVM(HttpStatus.valueOf(response.status()).getReasonPhrase(), "Error occured in remote service call");

        try {
            if(null != response.body()){
                errorVM.add(methodKey, "", Util.toString(response.body().asReader()));
            }
        } catch (IOException e){
            logger.debug("Error decoding the response body during remote call"+e.getMessage());
        }

        if(response.status() >=400 && response.status() <= 599){
            return new ApiException(errorVM, HttpStatus.valueOf(response.status()));
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
