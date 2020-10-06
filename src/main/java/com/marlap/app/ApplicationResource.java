package com.marlap.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ApplicationResource {
	
	@GetMapping(value = "/live", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLive() {
		return new ResponseEntity<String>("{\"alive\": \"true\"}", HttpStatus.OK);
	}
}
