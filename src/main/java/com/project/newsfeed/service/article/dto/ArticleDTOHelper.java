package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.service.profile.dto.ProfileDTOHelper;
import com.project.newsfeed.service.user.dto.UserDTOHelper;

public class ArticleDTOHelper {

    public ArticleDTOHelper() {
    }

    public static ArticleDTO fromEntity(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        if (article.getImage() != null) {
            articleDTO.setImage(new String(article.getImage()));
        }
        articleDTO.setUser((UserDTOHelper.fromEntity(article.getUser())));
        articleDTO.setNrUpvotes(article.getNrUpvotes());
        articleDTO.setTagList(article.getTagList());
        articleDTO.setCategoryList(article.getCategoryList());
        articleDTO.setProfileDTO(ProfileDTOHelper.fromEntity(article.getUser().getProfile()));

        return articleDTO;
    }

    public static Article toEntity(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        if (articleDTO.getImage() != null) {
            article.setImage(articleDTO.getImage().getBytes());
        }
        article.setUser(UserDTOHelper.toEntity(articleDTO.getUser()));
        article.setNrUpvotes(articleDTO.getNrUpvotes());
        article.setTagList(articleDTO.getTagList());
        article.setCategoryList(article.getCategoryList());


        return article;
    }

}
