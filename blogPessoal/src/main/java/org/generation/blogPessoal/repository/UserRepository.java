package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
	
public Optional <User> findByUsername(String username);
	
	public List <User> findAllByNameContainingIgnoreCase (String name);
	
	public User findByName(String name);


}
