package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.article.Category;
import com.project.newsfeed.entity.article.Tag;
import com.project.newsfeed.service.profile.dto.ProfileDTOHelper;
import com.project.newsfeed.service.user.dto.UserDTOHelper;

import java.util.ArrayList;
import java.util.List;

public class ArticleDTOHelper {


    public ArticleDTOHelper() {
    }

    public static ArticleDTO fromEntity(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());

        List<Tag> tagList = article.getTagList();
        List<String> tagListString = new ArrayList<>();
        tagList.forEach(tag -> tagListString.add(tag.getName()));
        articleDTO.setTagList(tagListString);

        List<Category> categoryList = article.getCategoryList();
        List<String> categoryListString = new ArrayList<>();
        categoryList.forEach(tag -> categoryListString.add(tag.getName()));
        articleDTO.setCategoryList(categoryListString);

        articleDTO.setContent(article.getContent());
        if (article.getImage() != null) {
            articleDTO.setImage(new String(article.getImage()));
        }
        articleDTO.setUser((UserDTOHelper.fromEntity(article.getUser())));
        articleDTO.setNrUpvotes(article.getNrUpvotes());

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


        return article;
    }


}
