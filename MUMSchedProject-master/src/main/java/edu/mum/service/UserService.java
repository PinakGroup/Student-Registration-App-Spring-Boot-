package edu.mum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.User;
import edu.mum.repository.UserRepository1;


@Service("userService")  // indicates that an annotated class is a service class.
public class UserService {

	private UserRepository1 userRepository;

	@Autowired
	public UserService(UserRepository1 userRepository) {
		this.userRepository = userRepository;
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}

}
