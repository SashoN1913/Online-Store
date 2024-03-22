package com.store.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.models.LoginModel;

@Controller
@RequestMapping("/login")
public class LoginController
{

	@GetMapping("/")
	public String displayLoginForm(Model model)
	{
		model.addAttribute("loginModel", new LoginModel());
		return "login.html";
	}

	@PostMapping("/processLogin")
	public String processLogin(LoginModel loginModel, Model model)
	{
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		model.addAttribute("email", loginModel.getEmail());
		model.addAttribute("password", loginModel.getPassword());
		System.out.println(loginModel.getEmail());
		return "loginResults.html";
	}
}
