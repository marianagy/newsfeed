package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
}
