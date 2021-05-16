package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.model.Category;
import com.boot.model.FormEnd;
import com.boot.model.Product;

import com.boot.service.ProductService;


import boot.global.GlobalData;

@Controller
public class CartController {

	@Autowired
	ProductService prodserv;
	
	
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id)
	{
		GlobalData.cart.add(prodserv.getProductById(id).get());
	return "redirect:/shop";
}
	
	@GetMapping("/cart")
	public String getcart(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
	model.addAttribute("cart",GlobalData.cart);
	return "cart";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model m) {
		m.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		FormEnd formend=new FormEnd();
		m.addAttribute("formend",new FormEnd());
	
		
	return "/checkout";
	}
	
	@PostMapping("/formend")

		public String postCategory(@ModelAttribute("formend")FormEnd formend) {
		
		
			return "orderplaced";
		}
	
	
	
	@PostMapping("/thankyou")
	public String thank() {
		return "thankyou";
	}
}
	
