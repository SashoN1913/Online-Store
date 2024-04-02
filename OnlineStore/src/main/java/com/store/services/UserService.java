package com.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.models.User;
import com.store.models.UserDTO;
import com.store.repository.UserRepository;

@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}

	public User getUser(int id)
	{
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	public void addUser(User user)
	{
		userRepository.save(user);
	}
	
	public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        //Role role = roleRepository.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
        //user.setRoles(Arrays.asList(role));
        System.out.println(user);
        userRepository.save(user);
    }
	
	public User findByUsername(String username)
	{
		return userRepository.findByEmail(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(username);
		System.out.println(user);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}	
		return (UserDetails) user;
	}
	

//	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
//	{
//		
//		 //User user = userRepo.findByEmail(email); if (user == null) { throw new
//		 //UsernameNotFoundException("User not exists by email"); }
//		 //Set<GrantedAuthority> authorities = user.getRoles().stream() .map((role) ->
//		 //new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//		 // 
//		 //return new org.springframework.security.core.userdetails.User(email,
//		 //user.getPassword(), authorities);
//		 
//		User user = userRepo.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException(email);
//        }
//        return new CustomUserDetails(user);
//	}
//	
	
	
}
