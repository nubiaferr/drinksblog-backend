package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import java.util.Optional;

import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User SignIn (User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordEncoder = encoder.encode(user.getPassword());
		user.setPassword(passwordEncoder);
		
		return repository.save(user);
	}
	
	public Optional<UserLogin> Login (Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<User> userLogin = repository.findByUsername(user.get().getUsername());
		
		if(userLogin.isPresent()) {
			if(encoder.matches(user.get().getPassword(), userLogin.get().getPassword())) {
				String auth = user.get().getUsername() + ":" + user.get().getPassword();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				user.get().setToken(authHeader);
				user.get().setId(userLogin.get().getId());
				user.get().setName(userLogin.get().getName());
				user.get().setPhoto(userLogin.get().getPhoto());
				user.get().setUserType(userLogin.get().getUserType());
				
				return user;
			}
		} 
		
		return null;
		
	}

	public User getUserById (Long userId) {
		return repository.findById(userId).orElse(null);
	}
}
