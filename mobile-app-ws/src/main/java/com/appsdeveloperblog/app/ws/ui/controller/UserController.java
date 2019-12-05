package com.appsdeveloperblog.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path = "{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		
		UserRest rest = new UserRest();
		UserDTO dto = userService.getUserByUserId(id);
		
		BeanUtils.copyProperties(dto, rest);
		return rest;
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		
		if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserRest returnValue = new UserRest();
		
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);
		UserDTO createdUser = userService.createUser(userDTO);
		
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}
	
	@PutMapping(path = "{id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);
		UserDTO updatedUser = userService.updateUser(id,userDTO);
		
		BeanUtils.copyProperties(updatedUser, returnValue);
		return returnValue;
	}
	
	@DeleteMapping(path = "{id}")
	public String deleteUser(@PathVariable String id) {
		String userId = userService.deleteUserByUserId(id);
		
		return "UserId "+userId+" deleted Successfully";
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorizartion",value="${userController.authorizationHeader.description}", paramType="header")
	})
	@GetMapping(
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public List<UserRest> getUsers(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="25") int limit){
		
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDTO> users = userService.getUsers(page,limit);
		
		for(UserDTO userDTO : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDTO, userModel);
			returnValue.add(userModel);
		}
		return returnValue;
	}

}
