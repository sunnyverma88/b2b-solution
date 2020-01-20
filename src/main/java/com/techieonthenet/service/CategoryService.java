package com.techieonthenet.service;

import com.techieonthenet.entity.Category;


/**
 * The interface Category service.
 */
public interface CategoryService {


    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<Category> findAll();

    /**
     * Find by id category.
     *
     * @param id the id
     * @return the category
     */
    Category findById(Long id);

    /**
     * Save category.
     *
     * @param category the category
     * @return the category
     */
    Category save(Category category);

}
