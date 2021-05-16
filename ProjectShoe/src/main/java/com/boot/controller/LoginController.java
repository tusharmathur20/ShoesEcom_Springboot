package com.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.model.Role;
import com.boot.model.User;
import com.boot.repository.RoleRepo;
import com.boot.repository.UserRepo;

import boot.global.GlobalData;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	UserRepo userrepo;
	
	@Autowired
	RoleRepo rolerepo;
	
	
	
	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	@PostMapping("/register")
	public String PostRegister(@ModelAttribute("user")User user,HttpServletRequest req)throws ServletException {
		String password=user.getPassword();
		user.setPassword(bcrypt.encode(password));
		List<Role>roles=new ArrayList<>();
		roles.add(rolerepo.findById(2).get());
		user.setRoles(roles);
		userrepo.save(user);
		req.login(user.getEmail(),password);
		return "redirect:/";
	}
	
}
