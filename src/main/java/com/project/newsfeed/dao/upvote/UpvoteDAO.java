package com.project.newsfeed.dao.upvote;

import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.upvote.Upvote;
import com.project.newsfeed.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UpvoteDAO extends JpaRepository<Upvote, Integer> {

    @Query("Select Count(a) FROM Article a where articleID=?1")
    Integer getUpvoteNrForArticle(Integer articleID);

    @Query("Select Count(u) FROM Upvote u where u.user = ?1 and u.article=?2")
    Integer userHasUpvotedArticle(User user, Article article);

}
