package com.store.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.store.models.User;
import com.store.repository.UserRepository;

@Service
public class UserService implements UserDetailsService 
{
	@Autowired    
	UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		User user = userRepo.findByEmail(email);
        if(user==null){
              throw new UsernameNotFoundException("User not exists by email");
             }
      Set<GrantedAuthority> authorities = user.getRoles().stream()
              .map((role) -> new SimpleGrantedAuthority(role.getName()))
              .collect(Collectors.toSet());

      return new org.springframework.security.core.userdetails.User(email,user.getPassword(),authorities);
	}
	
}
