package com.techieonthenet.repository;

import com.techieonthenet.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * The interface Category repository.
 */
@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Category> findByName(@Param("name") String name);
}
