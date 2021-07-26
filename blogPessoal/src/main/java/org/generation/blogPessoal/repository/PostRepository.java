package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	public List<Post> findAllByTituloCointainingIgnoreCase (String titulo);
}
