package com.store.services;

import java.util.*;
import java.util.stream.Collectors;

import com.store.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.repository.RoleRepository;
import com.store.repository.UserRepository;


@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}

	public List<UserDto> findAllUsersDto()
	{
		List<User> users = userRepository.findAll();
		return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
	}
	
	public List<User> getUsers()
	{
		List<User> users = userRepository.findAll();
		return users;
	}

	public User getUser(Long id)
	{
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	private UserDto mapToUserDto(User user)
	{
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		return userDto;
	}

	private Role checkRoleExist()
	{
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		return roleRepository.save(role);
	}
	
	public User createUser(String firstName, String lastName, String password, String email, List<String> roles) {
		User user = findUserByEmail(email);
		if (user != null) {
			return user;
		} else {
			user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(email);			
			List<Privilege> userRoles = new ArrayList<>();
			for (String rolename : roles) {
				Role role = roleRepository.findByName(rolename);
				if (role == null) {
					role = new Role();
					role.setName(rolename);
					roleRepository.save(role);
				}
				userRoles.add(new Privilege(user, role));
			}			
			user.setRoles(userRoles);
			return userRepository.save(user);
		}
	}

	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(email);
		
		if (user == null)
		{
			throw new UsernameNotFoundException("Username not found");
		}	
		return user;
	}
}
