package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Post;
import org.generation.blogPessoal.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
	
	@Autowired
	private PostRepository repository;
	
	@GetMapping
	private ResponseEntity<List<Post>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
}
