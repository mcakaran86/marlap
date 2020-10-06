package com.marlap.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlap.service.SecurityService;
import com.marlap.util.Constant;

@RestController
@RequestMapping("${app.rest.base.path}/security")
public class SecurityResource extends ResponseBuilder {

	@Autowired
	private SecurityService service;

	@PostMapping(value = "/question", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> question(@RequestBody String requestString) {
		if(StringUtils.isEmpty(requestString)) {
			return buildResponseEntity("Request message is empty", HttpStatus.BAD_REQUEST);
		}
		return buildResponseEntity(service.requestQuestion(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/answer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> answer(@RequestBody String answerString) {
		if(StringUtils.isEmpty(answerString)) {
			return buildResponseEntity("Answer is empty", HttpStatus.BAD_REQUEST);
		}
		String response = service.validateAns(answerString);
		return buildResponseEntity(response,
				response.equalsIgnoreCase(Constant.CORRECT_ANS) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
