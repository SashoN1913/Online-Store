package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.store.models.User;
import com.store.models.UserDTO;
import com.store.services.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String displayLoginForm(Model model)
	{
		// System.out.println(userService.findByUsername("admin"));
		// model.addAttribute("loginModel", new LoginModel());
		return "login.html";
	}

//	@PostMapping("/login-error")
//	public ModelAndView failLogin(@ModelAttribute("email") String email)
//	{
//		ModelAndView modelAndView = new ModelAndView("login");
//		modelAndView.addObject("email", email);
//		modelAndView.addObject("bad_credentials", "true");
//
//		return modelAndView;
//	}

	/*
	 * @PostMapping("/processLogin") public String processLogin(LoginModel
	 * loginModel, Model model) { BCryptPasswordEncoder b = new
	 * BCryptPasswordEncoder(); model.addAttribute("email", loginModel.getEmail());
	 * model.addAttribute("password", loginModel.getPassword());
	 * //System.out.println(loginModel.getEmail()); return "loginResults.html"; }
	 */

//	@GetMapping("/registrations")
//	public String getRegistrationPage(@ModelAttribute("user") User user)
//	{
//		return "registrations";
//	}
//
//	@PostMapping("/registrations")
//	public String saveUser(@ModelAttribute("user") User user, Model model)
//	{
//		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
//		String encoded = b.encode(user.getPassword());
//		user.setPassword(encoded);
//		userService.addUser(user);
//		model.addAttribute("message", "Submitted Succesuflu");
//		return "loginResults";
//	}

	@GetMapping("/signup")
	public String showRegistrationForm(Model model)
	{
		model.addAttribute("user", new UserDTO());
		// model.addAttribute("user", new User());

		return "signup";
	}

	@PostMapping("signupSave")
	public String registration(@Validated @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model)
	{
		User existingUser = userService.findByUsername(userDto.getEmail());

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty())
		{
			result.rejectValue("email", null, "There is already an account registered with the same email");
		}

		if (result.hasErrors())
		{
			model.addAttribute("user", userDto);
			return "/register";
		}

		userService.saveUser(userDto);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return "redirect:/register?success";
	}

//	@PostMapping("/process_register")
//	public String processRegister(User user)
//	{
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//
//		userService.addUser(user);
//
//		return "/";
//	}

}
