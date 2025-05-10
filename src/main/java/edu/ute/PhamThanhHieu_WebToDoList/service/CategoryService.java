package edu.ute.PhamThanhHieu_WebToDoList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.ute.PhamThanhHieu_WebToDoList.model.Category;
import edu.ute.PhamThanhHieu_WebToDoList.repo.CategoryRepository;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepo;
    
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    
    public List<Category> getCategoriesByUserId(int userId) {
        return categoryRepo.findByUserId(userId);
    }
}
