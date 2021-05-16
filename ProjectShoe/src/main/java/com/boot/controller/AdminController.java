package com.boot.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.dto.ProductDTO;
import com.boot.model.Category;
import com.boot.model.Product;
import com.boot.service.CategoryService;
import com.boot.service.ProductService;

@Controller
public class AdminController {

	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	@Autowired
	CategoryService service;
	
	@Autowired
	ProductService prodservice;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
	model.addAttribute("categories",service.getallCategory());
		return "categories";
	}
	
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
	
		  Category cat=new Category(); 
		 
		  model.addAttribute("category",cat);
		 
		return "categoriesAdd";
		}
	
	@PostMapping("/admin/categories/add")
	public String postCategoryAdd(@ModelAttribute("category")
	Category category) {
		service.addCategory(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deletecategory(@PathVariable int id) {
	service.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updatecategory(@PathVariable int id,Model model) {
		Optional<Category>category=service.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category",category.get());
			return "categoriesAdd";
		}else {
		return "404";
		}
	}
		
		//Product Section
		@GetMapping("/admin/products")
		public String products(Model m) {
			m.addAttribute("products",prodservice.getallProduct());
				return "products";
	}
		
		
		@GetMapping("/admin/products/add")
		public String getAddproduct(Model m) {
			ProductDTO prod=new ProductDTO();
			
			m.addAttribute("productDTO",prod);
			m.addAttribute("categories",service.getallCategory());
				return "productsAdd";
	}
	
		
		@PostMapping("/admin/products/add")
		public String postAddProduct(@ModelAttribute("productDTO")ProductDTO productDTO
				,@RequestParam("productImage")MultipartFile file,
				@RequestParam ("imagename")String imagename) throws IOException {
			
			
			
			Product product=new Product();
			product.setId(productDTO.getId());
			product.setName(productDTO.getName());
			product.setCategory(service.getCategoryById(productDTO.getCategoryId()).get());
			product.setPrice(productDTO.getPrice());
			product.setSize(productDTO.getSize());
			product.setDescription(productDTO.getDescription());
			String imageUUID;
			
			if(!file.isEmpty()){
				imageUUID =file.getOriginalFilename();
				Path filename_path=Paths.get(uploadDir, imageUUID);
				Files.write(filename_path, file.getBytes());
			}else {
				imageUUID=imagename;
			}
			product.setImagename(imageUUID);
			prodservice.addProducts(product);
			
			
			
			return "redirect:/admin/products";
		}
		
		@GetMapping("/admin/product/delete/{id}")
		public String deleteproduct(@PathVariable long id) {
			prodservice.removeProduct(id);
			return "redirect:/admin/products";
		}
		
		
		@GetMapping("/admin/product/update/{id}")
		public String updateProduct(@PathVariable long id,Model model) {
		Product product=prodservice.getProductById(id).get();
		ProductDTO productDTO=new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId((product.getCategory().getId()));
		productDTO.setPrice(product.getPrice());
		productDTO.setSize(product.getSize());
		productDTO.setDescription(product.getDescription());
		productDTO.setImagename(product.getImagename());
		
		model.addAttribute("categories",service.getallCategory());
		model.addAttribute("productDTO",productDTO);
			
			
			return "productsAdd";
		}
		
}
