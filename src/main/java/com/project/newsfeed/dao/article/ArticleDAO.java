package com.project.newsfeed.dao.article;

import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.article.Tag;
import com.project.newsfeed.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleDAO extends JpaRepository<Article, Integer> {

    @Query("Select a FROM Article a where a.user = ?1")
    List<Article> getArticlesForUser(User user);

    @Query("Select count(a) from Article a")
    Integer countAllArticles();

    @Query("Select count(a) from Article a where a.user=?1")
    Integer countAllUserArticles(User user);

    @Query("Select t.tagList from Article t where t.id=?1")
    List<Tag> getTagsForArticle(Integer articleId);

    @Query("Select t From Article t join t.tagList tag where tag=?1")
//@Query(nativeQuery = true,
        //      value = "SELECT * FROM Article a join a. WHERE ")
    List<Article> findArticleByTag(Tag tag, Pageable pageable);

}
