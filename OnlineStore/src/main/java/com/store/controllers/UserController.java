package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.store.models.User;
import com.store.repository.UserRepository;

@Controller
public class UserController
{
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/registrations")
	public String getRegistrationPage(@ModelAttribute("user") User user)
	{
		return "registrations";
	}
	
	@PostMapping("/registrations")
	public String saveUser(@ModelAttribute("user") User user, Model model)
	{
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		String encoded = b.encode(user.getPassword());
		user.setPassword(encoded);
		userRepository.save(user);
		model.addAttribute("message", "Submitted Succesuflu");
		return "loginResults";
	}
}
