package org.generation.blogPessoal.repository;
import java.util.List;

import org.generation.blogPessoal.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
	public List<Theme> findAllByTitleContainingIgnoreCase(String title);
}
