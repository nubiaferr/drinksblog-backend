package org.generation.blogPessoal.security;

import java.util.Collection;

import org.generation.blogPessoal.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javassist.SerialVersionUID;

public class UserDetailsImpl implements UserDetails {

	private static final long SerialVersionUID = 1L;
	
	private String userName;
	private String passWord;
	
	public  UserDetailsImpl(User user) {
		this.userName = user.getUsername();
		this.passWord = user.getPassword();
	}
	
	public  UserDetailsImpl() {	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passWord;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
