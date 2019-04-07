package com.project.newsfeed.dao.upvote;

import com.project.newsfeed.entity.upvote.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UpvoteDAO extends JpaRepository<Upvote, Integer> {

    @Query("Select Count(a) FROM Article a where articleID=?1")
    Integer getUpvoteNrForArticle(Integer articleID);

}
