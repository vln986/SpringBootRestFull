package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		
		if(userRepository.findByEmail(userDTO.getEmail()) != null) throw new RuntimeException("Record already Exists");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDTO, userEntity);
		
		userEntity.setEncryptedPassword(bcryptPasswordEncoder.encode(userDTO.getPassword()));
		userEntity.setUserId(userDTO.getFirstName().toUpperCase());
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}
	
	@Override
	public UserDTO getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity.getEmail() == null) throw new UsernameNotFoundException(email);
		
		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity.getEmail() == null) throw new UsernameNotFoundException(email);
	
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDTO getUserByUserId(String userId) {
		UserDTO returnValue = new UserDTO();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public String deleteUserByUserId(String userId) {
		userRepository.deleteById(Long.valueOf(userId));
		return userId;
	}
	
	@Override
	public UserDTO updateUser(String userId, UserDTO userDTO) {
		
		UserDTO returnValue = new UserDTO();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		
		UserEntity updatedUserDetails = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(updatedUserDetails, returnValue);
		
		return returnValue;
		
	}

	@Override
	public List<UserDTO> getUsers(int page, int limit) {
		
		if(page > 0) page = page -1;
		
		List<UserDTO> returnValue = new ArrayList<>();
		Pageable  pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> users = usersPage.getContent();
		
		for(UserEntity user : users) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			returnValue.add(userDTO);
		}
		
		return returnValue;
	}
	
	

}
