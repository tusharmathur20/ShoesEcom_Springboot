package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.boot.service.CategoryService;
import com.boot.service.ProductService;

import boot.global.GlobalData;

@Controller
public class HomeController {

	@Autowired
	CategoryService catservice;
	
	@Autowired
	ProductService prodservice;
	
	
	@GetMapping({"/","/home"})
public String home(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
model.addAttribute("categories",catservice.getallCategory());
model.addAttribute("products",prodservice.getallProduct());
return "shop";
	}
	
	
	
	@GetMapping("shop/category/{id}")
public String shopByCategory(Model model,@PathVariable int id) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("categories",catservice.getallCategory());
		model.addAttribute("products",prodservice.getAllProductsbyCatId(id));
		
		return "shop";
		
	}
	
	@GetMapping("shop/viewproduct/{id}")
		public String viewProduct(Model model,@PathVariable int id) {
			model.addAttribute("product",prodservice.getProductById(id).get());
			model.addAttribute("cartCount",GlobalData.cart.size());
			return "viewProduct";
			
		}
	
	
	}
