package edu.ute.PhamThanhHieu_WebToDoList.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ute.PhamThanhHieu_WebToDoList.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByUserId(int userId);
}

