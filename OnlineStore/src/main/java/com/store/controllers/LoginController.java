package com.store.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.store.models.LoginModel;

@Controller
public class LoginController
{

	@GetMapping("/login")
	public String displayLoginForm(Model model)
	{
		model.addAttribute("loginModel", new LoginModel());
		return "login.html";
	}

	@PostMapping("/login-error")
	public ModelAndView failLogin(@ModelAttribute("email") String email)
	{
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("email", email);
		modelAndView.addObject("bad_credentials", "true");

		return modelAndView;
	}

	/*
	 * @PostMapping("/processLogin") public String processLogin(LoginModel
	 * loginModel, Model model) { BCryptPasswordEncoder b = new
	 * BCryptPasswordEncoder(); model.addAttribute("email", loginModel.getEmail());
	 * model.addAttribute("password", loginModel.getPassword());
	 * //System.out.println(loginModel.getEmail()); return "loginResults.html"; }
	 */
}
