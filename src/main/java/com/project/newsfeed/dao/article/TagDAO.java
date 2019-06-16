package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagDAO extends JpaRepository<Tag, Integer> {

    @Query("Select t from Tag t where t.name = ?1")
    Tag findByName(String tagName);


}
