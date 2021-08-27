package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 2)
	private String name;
	
	@NotBlank
	@Size(min = 2)
	private String photo;
	
	@NotBlank
	private String userType;
	
	@NotBlank
	@Email(message = "Must be an e-mail")
	@Size(min = 5)
	private String username;
	
	@NotBlank
	@Size(min = 8, message = "Minimum of 8 characters")
	private String password;
		
	@OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("user")
	private List<Post> post;


	public User(long id, @NotBlank @Size(min = 2) String name, @NotBlank @Size(min = 2) String photo,
			@NotBlank String userType, @NotBlank @Email(message = "Must be an e-mail") @Size(min = 5) String username,
			@NotBlank @Size(min = 8, message = "Minimum of 8 characters") String password, List<Post> post) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.userType = userType;
		this.username = username;
		this.password = password;
		this.post = post;
	}

	public User() {
		super();
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
	
}
