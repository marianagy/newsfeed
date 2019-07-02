package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

    @Query("Select c from Category c where c.name = ?1")
    Category findByName(String categoryName);


}
