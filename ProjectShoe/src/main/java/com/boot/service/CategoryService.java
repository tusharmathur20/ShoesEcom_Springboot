package com.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.model.Category;
import com.boot.repository.CategoryRepo;



@Service
public class CategoryService {

	@Autowired
	CategoryRepo catrepo;
	
	public List<Category>getallCategory(){
		return catrepo.findAll();
	}
	
	public void addCategory(Category category) {
		catrepo.save(category);
	}
	public void removeCategoryById(int id) {
		catrepo.deleteById(id);
	}
	public Optional<Category> getCategoryById(int id) {
		return catrepo.findById(id);
	}
}
