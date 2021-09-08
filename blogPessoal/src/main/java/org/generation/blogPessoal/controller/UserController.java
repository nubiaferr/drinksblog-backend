package org.generation.blogPessoal.controller;

import java.util.Optional;

import org.generation.blogPessoal.model.Theme;
import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.repository.UserRepository;
import org.generation.blogPessoal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	private UserRepository repository;
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> Authentication (@RequestBody Optional<UserLogin> userLogin){
		return userService.Login(userLogin)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/signin")
	public ResponseEntity<User> Post(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.SignIn(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable Long id){
		System.out.println("id is " + id);
		User user = userService.getUserById(id);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		}
	}
}
