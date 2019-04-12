package com.project.newsfeed.service.article;

import com.project.newsfeed.dao.article.ArticleDAO;
import com.project.newsfeed.dao.article.TagDAO;
import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.article.Tag;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.article.dto.ArticleDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO;
    private TagDAO tagDAO;
    private UserDAO userDAO;

    @Autowired
    public ArticleServiceImpl(ArticleDAO articleDAO, TagDAO tagDAO, UserDAO userDAO) {
        this.articleDAO = articleDAO;
        this.tagDAO = tagDAO;
        this.userDAO = userDAO;
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
        // testez daca tag-ul e deja in db, si sa il salvez daca nu e
        List<Tag> tagList = articleDTO.getTagList();
        List<Tag> dbTagList = tagDAO.findAll();
        if (dbTagList.size() > 0) {
            for (int i = 0; i <= tagList.size(); i++) {
                for (int j = 0; j <= dbTagList.size(); j++) {
                    if (!(tagList.get(i).getName().equals(dbTagList.get(j).getName()))) {
                        tagDAO.save(tagList.get(i));
                    }
                }
            }

            articleDAO.save(article);
        } else {
            articleDAO.save(article);
        }


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
