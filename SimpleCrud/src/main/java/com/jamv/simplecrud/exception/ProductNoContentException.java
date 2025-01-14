package com.jamv.simplecrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ProductNoContentException extends RuntimeException {
     public ProductNoContentException(String message) {
         super(message);
     }
}
