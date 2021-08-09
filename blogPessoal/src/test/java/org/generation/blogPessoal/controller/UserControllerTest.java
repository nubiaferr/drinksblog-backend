package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT) //port aleatorio
@TestInstance (TestInstance.Lifecycle.PER_CLASS) //quero testar classes
public class UserControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeAll
	void start() {
			LocalDate date = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			User user = new User(0, "João da Silva", "joao@email.com", "12345678", date);
			if(userRepository.findByUsername(user.getUsername()).isEmpty()) {
				userRepository.save(user);
			} //se o usuario nao existir, criar
			
	
			user = new User(0, "Frederico da Silva", "frederico@email.com.br", "13465278", date);
			if(userRepository.findByUsername(user.getUsername()).isEmpty()) {
				userRepository.save(user);
			} 
					
			user = new User(0, "Paulo Antunes", "paulo@email.com.br", "13465278", date);
			if(userRepository.findByUsername(user.getUsername()).isEmpty()) {
				userRepository.save(user);
			}
		} 
		
		
		@Test
		@DisplayName("Returns name")
		public void findByNameReturnsName () {
			User user = userRepository.findByName("João da Silva");
			assertTrue(user.getName().equals("João da Silva"));
		}
		
		@Test
		@DisplayName("Returns 3 users")
		public void findByNameContaigningIgnoreCaseReturnsThreeUsers () {
			List<User> usersList = userRepository.findAllByNameContainingIgnoreCase("Silva");
			assertEquals(3, usersList.size());
		}
		
		@AfterAll
		public void end() {
			System.out.println("The end");
		}

}
