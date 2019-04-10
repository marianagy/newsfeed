package com.project.newsfeed.service.upvote;

import com.project.newsfeed.dao.upvote.UpvoteDAO;
import com.project.newsfeed.entity.upvote.Upvote;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.article.dto.ArticleDTOHelper;
import com.project.newsfeed.service.upvote.dto.UpvoteDTO;
import com.project.newsfeed.service.upvote.dto.UpvoteDTOHelper;
import com.project.newsfeed.service.user.dto.UserDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UpvoteServiceImpl implements UpvoteService {

    private UpvoteDAO upvoteDAO;

    @Autowired
    public UpvoteServiceImpl(UpvoteDAO upvoteDAO) {
        this.upvoteDAO = upvoteDAO;
    }

    @Override
    public List<UpvoteDTO> findAll() {
        return upvoteDAO.findAll().stream()
                .map(UpvoteDTOHelper::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public UpvoteDTO findById(int id) throws BusinessException {
        Optional<Upvote> result = upvoteDAO.findById(id);
        if (result.isPresent()) {

            return UpvoteDTOHelper.fromEntity(result.get());
        } else {
            throw new BusinessException(ExceptionCode.UPVOTE_NOT_FOUND);
        }
    }

    @Override
    public void save(UpvoteDTO upvoteDTO) throws BusinessException {

        if (userHasUpvotedArticle(UserDTOHelper.toEntity(upvoteDTO.getUserDTO()), upvoteDTO.getArticleDTO())) {
            throw new BusinessException(ExceptionCode.ALREADY_UPVOTED);
        }

        upvoteDAO.save(UpvoteDTOHelper.toEntity(upvoteDTO));
    }


    @Override
    public void deleteUpvote(User user, ArticleDTO article) {
        Upvote upvote = upvoteDAO.getUpvoteForUserAndArticle(user, ArticleDTOHelper.toEntity(article));

        upvoteDAO.deleteById(upvote.getId());
    }


    @Override
    public Integer getUpvoteNrForArticle(Integer articleID) {
        return upvoteDAO.getUpvoteNrForArticle(articleID);
    }

    @Override
    public Boolean userHasUpvotedArticle(User user, ArticleDTO article) {
        return upvoteDAO.userHasUpvotedArticle(user, ArticleDTOHelper.toEntity(article)) > 0;
    }

}