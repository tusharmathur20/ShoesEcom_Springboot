package com.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.model.Product;
import com.boot.repository.ProductRepo;


@Service
public class ProductService {
	@Autowired
	ProductRepo productrepo;
	
	
	public List<Product>getallProduct(){
		return productrepo.findAll();
	}
	
	public void addProducts(Product product) {
		productrepo.save(product);
	}
	
	public void removeProduct(long id) {
		productrepo.deleteById(id);
	}
	public Optional<Product> getProductById(long id){
		return productrepo.findById(id);
	}
	
	public List<Product>getAllProductsbyCatId(int id){
		return productrepo.findAllByCategory_Id(id);
			
		
	}
	
}
