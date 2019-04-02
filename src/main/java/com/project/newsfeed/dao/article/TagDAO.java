package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDAO extends JpaRepository<Tag, Integer> {
}
