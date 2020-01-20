package com.techieonthenet.service;

import com.techieonthenet.entity.Category;


public interface CategoryService {


    Iterable<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

}
