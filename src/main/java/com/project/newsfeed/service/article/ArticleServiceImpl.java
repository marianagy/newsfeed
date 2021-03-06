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
import com.project.newsfeed.service.article.dto.*;
import com.project.newsfeed.service.upvote.TagLikeService;
import com.project.newsfeed.service.upvote.dto.TagLikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private FilterArticle filterArticle;
    private TagLikeService tagLikeService;
    private TagService tagService;
    private ArticleService articleService;

    @Autowired
    public void setTagLikeService(TagLikeService tagLikeService) {
        this.tagLikeService = tagLikeService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public ArticleServiceImpl(ArticleDAO articleDAO, TagDAO tagDAO, UserDAO userDAO, CategoryDAO categoryDAO, FilterArticle filterArticle) {
        this.articleDAO = articleDAO;
        this.tagDAO = tagDAO;
        this.userDAO = userDAO;
        this.categoryDAO = categoryDAO;
        this.filterArticle = filterArticle;
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

    /**
     * Methid saves tags in db
     *
     * @param articleDTO
     * @return list of tags
     */
    public List<Tag> getTagsFromDTO(ArticleDTO articleDTO) {
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


    /**
     * Method saves categories in db
     * @param articleDTO
     * @return list of categories
     * @throws BusinessException
     */
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

    @Override
    public ArticleListDTO getFilteredArticles(Integer pageIndex, Integer pageSize) {


        Pageable page = PageRequest.of(pageIndex, pageSize);
        Integer amount = articleDAO.countAllArticles();
        List<ArticleDTO> articleDTOList = filterArticle.findAll(page).stream()
                .map(ArticleDTOHelper::fromEntity)
                .collect(Collectors.toList());
        return new ArticleListDTO(articleDTOList, amount);

    }

    /**
     * Get filtered articles for a user (using pagination)
     *
     * @param user
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ArticleListDTO getfilteredArticlesForUser(User user, Integer pageIndex, Integer pageSize) {
        Pageable page = PageRequest.of(pageIndex, pageSize);

        Integer amount = articleDAO.countAllUserArticles(user);

        List<ArticleDTO> articleDTOS = filterArticle.filteredArticlesForUser(user, page).stream()
                .map(ArticleDTOHelper::fromEntity)
                .collect(Collectors.toList());

        return new ArticleListDTO(articleDTOS, amount);

    }

    /**
     * Method gets the articles that a specifid user would be interested in
     *
     * @param user
     * @return
     */
    @Override
    public ArticleListDTO getRecommendedArticlesForUser(User user, Integer pageIndex, Integer pageSize) {

        // get taglike list
        List<TagLikeDTO> tagLikeDTOList = tagLikeService.findTagLikeByUserId(user.getId());

        // form tag list
        List<Tag> tagList = tagLikeDTOList.stream()
                .map(tagLikeDTO -> {
                    try {
                        return TagDTOHelper.toEntity(tagService.findById(tagLikeDTO.getTagId()));
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());


        Pageable page = PageRequest.of(pageIndex, pageSize);
        // for each tag in tag list, get article list
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for (Tag tag : tagList) {

            List<Article> articleByTag = articleDAO.findArticleByTag(tag);
            articleByTag.forEach(article -> {

                if (article != null &&
                        !articleDTOList.contains(ArticleDTOHelper.fromEntity(article))

                ) {
                            articleDTOList.add(ArticleDTOHelper.fromEntity(article));
                        }
                    });
        }

        // concatenate with rest of the articles
        List<ArticleDTO> all = this.findAll();
        all.removeAll(articleDTOList);
        articleDTOList.addAll(all);

        //solve pagination
        int pagestart = pageIndex * pageSize;
        int pageEnd = pagestart + pageSize;
        if (pageEnd > articleDTOList.size()) {
            pageEnd = articleDTOList.size();
        }
        List<ArticleDTO> articleDTOListFinal = articleDTOList.subList(pagestart, pageEnd);


        Integer amount = articleDAO.countAllArticles();


        return new ArticleListDTO(articleDTOListFinal, amount);
    }

    /**
     * Method return all the articles that have the specified categories
     *
     * @param
     * @return
     */
    @Override
    public ArticleListDTO getAllArticlesByCategory(CategoryDTO categoryDTO, Integer pageIndex, Integer pageSize) {

        Pageable page = PageRequest.of(pageIndex, pageSize);
        Category category = categoryDAO.findByName(categoryDTO.getName());
        Integer amount = articleDAO.countAllArticlesForCategory(category);
        List<ArticleDTO> articleDTOS = articleDAO.getAllArticlesByCategory(category, page)
                .stream()
                .map(ArticleDTOHelper::fromEntity)
                .collect(Collectors.toList());
        return new ArticleListDTO(articleDTOS, amount);
    }


}
