package com.store.services;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.store.models.CustomUserDetails;
import com.store.models.User;
import com.store.repository.UserRepository;
@Service
public class CustomUserDetailsService// implements UserDetailsService
{
	
	@Autowired
	private UserRepository repo;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
//	{
//		User user = repo.findByEmail(username);
//		if(user.equals(null))
//		{
//			throw new UsernameNotFoundException("User not found");
//		}
//		//return new CustomUserDetails(user);
//		return null;
//		//return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
//	}

}
