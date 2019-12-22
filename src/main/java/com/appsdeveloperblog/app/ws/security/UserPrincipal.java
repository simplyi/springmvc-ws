package com.appsdeveloperblog.app.ws.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.appsdeveloperblog.app.ws.io.entity.AuthorityEntity;
import com.appsdeveloperblog.app.ws.io.entity.RoleEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = -7530187709860249942L;
	
	private UserEntity userEntity;
	private String userId;

	public UserPrincipal(UserEntity userEntity) {
		this.userEntity = userEntity;
		this.userId = userEntity.getUserId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> authorities = new HashSet<>();
		Collection<AuthorityEntity> authorityEntities = new HashSet<>();
		
		// Get user Roles
		Collection<RoleEntity> roles = userEntity.getRoles();
		
        if(roles == null) return authorities;
        
        roles.forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorityEntities.addAll(role.getAuthorities());
       });
        
        authorityEntities.forEach((authorityEntity) ->{
            authorities.add(new SimpleGrantedAuthority(authorityEntity.getName()));
        });
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.userEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return this.userEntity.getEmail();
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
		return this.userEntity.getEmailVerificationStatus();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
