package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Theme;
import org.generation.blogPessoal.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theme")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {
	
	@Autowired
	private ThemeRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Theme>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Theme> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<Theme>> getByName(@PathVariable String description){
		return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(description));
	}
	
	@PostMapping
	public ResponseEntity<Theme> post (@RequestBody Theme theme){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(theme));
	}
	
	@PutMapping
	public ResponseEntity<Theme> put (@RequestBody Theme theme){
		return ResponseEntity.ok(repository.save(theme));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	
}
