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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT) //port aleatorio
@TestInstance (TestInstance.Lifecycle.PER_CLASS) //quero testar classes
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	private User user;
	private User userUpdate;
	private User userAdmin;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeAll
	public void start(){
		LocalDate dataAdmin = LocalDate.parse("1990-07-22",
		DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		userAdmin = new User(0L, "Administrador","admin@email.com.br", "admin123", dataAdmin);
		if(!userRepository.findByUsername(userAdmin.getUsername()).isPresent()){
				HttpEntity<User> request = new HttpEntity<User>(userAdmin);
				testRestTemplate.exchange("/users/signin", HttpMethod.POST, request, User.class);
			}
		
		LocalDate dataPost = LocalDate.parse("2000-07-22",
		DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		user = new User(0L, "Paulo Antunes", "paulo@email.com.br", "13465278", dataPost);
		LocalDate dataPut = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		userUpdate = new User(2L, "Paulo Antunes de Souza",
		"paulo_souza@email.com.br", "souza123", dataPut);
		}
		
		@Test
		@Order(1)
		@DisplayName("✔ Sign in users!")
		public void deveRealizarPostUsuario() {
		HttpEntity<User> request = new HttpEntity<User>(user);
		ResponseEntity<User> resposta = testRestTemplate.exchange("/users/signin", HttpMethod.POST, request, User.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		}
		
		@Test
		@Order(2)
		@DisplayName("👍 List all users!")
		public void deveMostrarTodosUsuarios() {
			ResponseEntity<String> resposta = testRestTemplate
					.withBasicAuth("admin@email.com.br", "admin123")
					.exchange("/users/all", HttpMethod.GET, null, String.class);
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
		}
		
		@Test
		@Order(3)
		@DisplayName("😳 Update user!")
		public void deveRealizarPutUsuario() {
		HttpEntity<User> request = new HttpEntity<User>(userUpdate);
		ResponseEntity<User> resp = testRestTemplate
		.withBasicAuth("admin@email.com.br", "admin123")
		.exchange("/users/update", HttpMethod.PUT, request, User.class);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
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
