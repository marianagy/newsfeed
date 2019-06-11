package com.project.newsfeed.service.article;

import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterArticle extends PagingAndSortingRepository<Article, Integer> {

//    List<Article> getFilteredArticles(Pageable pageable);

    @Query("Select a FROM Article a where a.user = ?1")
    List<Article> filteredArticlesForUser(User user, Pageable pageable);

}
