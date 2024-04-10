package com.store.controllers;

import java.util.Arrays;
import java.util.List;

import com.store.models.Address;
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
		return "account/login.html";
	}

	@GetMapping("/signup")
	public String showRegistrationForm(Model model)
	{
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "account/signup.html";
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
			return "account/signup.html";
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



	@GetMapping("/account")
	public String myProfile(Model model, Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		return "account/account.html";
	}

	@GetMapping("/account/order")
	public String myOrders(Model model, Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		//List<Order> orders = orderService.findByUser(user);
		//model.addAttribute("orders", orders);
		return "account/orders.html";
	}

//	@GetMapping("/account/orderDetails")
//	public String orderDetail(@RequestParam("order") Long id, Model model) {
//		Order order = orderService.findOrderWithDetails(id);
//		model.addAttribute("order", order);
//		return "orderDetails";
//	}

	@GetMapping("/account/address")
	public String myAddress(Model model, Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();
		model.addAttribute("address", user.getAddress());
		return "account/address.html";
	}

	@GetMapping("/account/addressAdd")
	public String add(Model model)
	{
		model.addAttribute("address", new Address());
		return "/account/addressAdd.html";
	}

	@PostMapping("/account/addressAddUpdate")
	public String addPost(@Validated Address address, BindingResult bindingResult, Model model, Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();
		user.addAddress(address);
		userService.save(user);
		user = userService.getUser(user.getId());
		model.addAttribute("address", user.getAddress());
		return "account/address.html";
	}

	@PostMapping("/productEdit")
	public String edit(@Validated Address address, BindingResult bindingResult, Model model)
	{
		//model.addAttribute("product", addressService.getProduct(address.getId()));
		return "admin/productEdit.html";
	}

	@PostMapping("/productUpdate")
	public String editPost(@Validated Address address, BindingResult bindingResult, Model model)
	{
		//addressService.updateProduct(address.getId(), address);
		//model.addAttribute("products", addressService.getProducts());
		return "admin/productView.html";
	}

	@PostMapping("/account/addressDelete")
	public String delete(@Validated Address address, BindingResult bindingResult, Model model, Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();
		user.removeAddress(address.getId());
		userService.save(user);
		User user2 = userService.getUser(user.getId());
		model.addAttribute("address", user2.getAddress());
		return "account/address.html";
	}
}
