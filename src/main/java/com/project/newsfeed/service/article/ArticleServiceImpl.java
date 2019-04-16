package com.project.newsfeed.service.article;

import com.project.newsfeed.dao.article.ArticleDAO;
import com.project.newsfeed.dao.article.CategoryDAO;
import com.project.newsfeed.dao.article.TagDAO;
import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.article.Category;
import com.project.newsfeed.entity.article.Tag;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.article.dto.ArticleDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO;
    private TagDAO tagDAO;
    private CategoryDAO categoryDAO;
    private UserDAO userDAO;

    @Autowired
    public ArticleServiceImpl(ArticleDAO articleDAO, TagDAO tagDAO, UserDAO userDAO, CategoryDAO categoryDAO) {
        this.articleDAO = articleDAO;
        this.tagDAO = tagDAO;
        this.userDAO = userDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<ArticleDTO> findAll() {
        return articleDAO.findAll().stream()
                .map(ArticleDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO findById(int id) throws BusinessException {
        Optional<Article> result = articleDAO.findById(id);
        if (result.isPresent()) {

            return ArticleDTOHelper.fromEntity(result.get());
        } else {
            throw new BusinessException(ExceptionCode.ARTICLE_NOT_FOUND);
        }
    }

    @Override
    public void save(ArticleDTO articleDTO) throws BusinessException {

        String username = articleDTO.getUser().getUsername();
        User user = userDAO.findByUsername(username);
        Article article = ArticleDTOHelper.toEntity(articleDTO);
        article.setUser(user);

        List<Tag> tags = getTagsFromDTO(articleDTO);

        List<Category> categories = getCategoriesFromDTO(articleDTO);

        if (categories.size() > 0) {
            categories.forEach(category -> {
                if (category.getCategoryArticleList() == null) {
                    category.setCategoryArticleList(new ArrayList<>());

                }
                category.getCategoryArticleList().add(article);
            });
        }
        if (tags.size() > 0) {
            tags.forEach(tag -> {
                if (tag.getTaggedArticleList() == null) {
                    tag.setTaggedArticleList(new ArrayList<>());

                }
                tag.getTaggedArticleList().add(article);
            });
        }
        article.setTagList(tags);
        article.setCategoryList(categories);
        articleDAO.save(article);


    }

//    public List<String> parseTagString(String tagString){
//
//        return Arrays.stream(tagString.split(" ")).collect(Collectors.toList());
//
//    }

    // save tag
    private List<Tag> getTagsFromDTO(ArticleDTO articleDTO) {
        List<String> tagList = articleDTO.getTagList();
        if (tagList == null) {
            tagList = new ArrayList<>();
        }
        return tagList.stream()
                .map(tagName -> {
                    Tag tag = tagDAO.findByName(tagName);
                    if (tag == null) {
                        tag = new Tag();
                        tag.setName(tagName);
                        tagDAO.save(tag);

                    }
                    return tag;
                })
                .collect(Collectors.toList());
    }

    // save category
    private List<Category> getCategoriesFromDTO(ArticleDTO articleDTO) throws BusinessException {
        //la fel categorii (aproape)
        List<String> categoryList = articleDTO.getCategoryList();
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
        List<Category> categories = categoryList.stream()
                .map(categoryName -> categoryDAO.findByName(categoryName))
                .collect(Collectors.toList());
        if (categoryList.contains(null)) {
            throw new BusinessException(ExceptionCode.CATEGORY_NOT_FOUND);
        }
        return categories;
    }

    @Override
    public void deleteById(int id) {

        articleDAO.deleteById(id);

    }

    @Override
    public List<ArticleDTO> getArticlesForUser(User user) {
        return articleDAO.getArticlesForUser(user).stream()
                .map(ArticleDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }
}
