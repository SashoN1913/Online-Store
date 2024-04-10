package com.store.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.store.models.User;
import com.store.models.UserDto;
import com.store.services.UserService;


@Controller
public class UserController
{
	@Autowired
	private UserService userService;

	@GetMapping("/index")
	public String home()
	{
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model)
	{
		return "login.html";
	}

	@GetMapping("/signup")
	public String showRegistrationForm(Model model)
	{
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "signup";
	}

	@PostMapping("/signup/save")
	public String registration(@Validated @ModelAttribute("user") UserDto userDto, BindingResult result, Model model)
	{
		User existingUser = userService.findUserByEmail(userDto.getEmail());

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty())
		{
			result.rejectValue("email", null, "There is already an account registered with the same email");
		}

		if (result.hasErrors())
		{
			model.addAttribute("user", userDto);
			return "signup";
		}

		userService.createUser(userDto.getFirstName(), userDto.getLastName(), userDto.getPassword(), userDto.getEmail(), Arrays.asList("ROLE_USER"));
		return "redirect:/signup?success";
	}

	@GetMapping("/users")
	public String users(Model model)
	{
		List<UserDto> users = userService.findAllUsersDto();
		model.addAttribute("users", users);
		return "users";
	}



	@GetMapping("/profile")
	public String myProfile(Model model, Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/profile/orders")
	public String myOrders(Model model, Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		//List<Order> orders = orderService.findByUser(user);
		//model.addAttribute("orders", orders);
		return "orders";
	}

	@GetMapping("/profile/address")
	public String myAddress(Model model, Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		return "address";
	}
}
