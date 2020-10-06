package com.marlap.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
	 final protected ResponseEntity<?> buildResponseEntity(Object data,
	            HttpStatus httpStatus) {
	        ResponseEntity<?> respEntity = new ResponseEntity<>(data,
	                ((httpStatus == null) ? HttpStatus.OK : httpStatus));
	        return respEntity;
	    }
}
