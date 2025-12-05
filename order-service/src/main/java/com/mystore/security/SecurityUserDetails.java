package com.mystore.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUserDetails implements UserDetails {

	 private Long id;
	    private String username;
	    private String password;
	    private Boolean enabled;
	    private Collection<? extends GrantedAuthority> authorities;

	    public SecurityUserDetails(Long id,
	                             String username,
	                             String password,
	                             Boolean enabled,
	                             Collection<? extends GrantedAuthority> authorities) {
	        this.id = id;
	        this.username = username;
	        this.password = password;
	        this.enabled = enabled;
	        this.authorities = authorities;
	    }

	    public Long getId() {
	        return id;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return this.authorities;
	    }

	    @Override
	    public String getPassword() {
	        return this.password;
	    }

	    @Override
	    public String getUsername() {
	        return this.username;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true; 
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return Boolean.TRUE.equals(this.enabled);
	    }

}
