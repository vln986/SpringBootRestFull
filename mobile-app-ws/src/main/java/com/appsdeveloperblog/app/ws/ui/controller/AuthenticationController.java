package com.appsdeveloperblog.app.ws.ui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.ui.model.request.LoginRequestModel;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ResponseHeader;

@RestController
public class AuthenticationController {
	
	@ApiOperation("User Login")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Response Headers",
					responseHeaders = {
							@ResponseHeader(name = "authorization",
											description = "Bearer <JWT token here>",
											response = String.class),
							@ResponseHeader(name = "userId",
											description = "<public userId value here>",
											response = String.class)
					})
	})
	@PostMapping("/users/login")
	public void theFakeLogin(@RequestBody LoginRequestModel loginRequestModel) {
		
		throw new IllegalStateException(
				"This method should not be called.This method is implemented by Spring security");

	}

}
