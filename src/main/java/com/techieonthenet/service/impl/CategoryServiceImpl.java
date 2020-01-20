package com.techieonthenet.service.impl;

import com.techieonthenet.entity.Category;
import com.techieonthenet.repository.CategoryRepository;
import com.techieonthenet.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }
}
