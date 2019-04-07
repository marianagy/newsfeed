package com.project.newsfeed.service.upvote.dto;


import com.project.newsfeed.entity.upvote.Upvote;
import com.project.newsfeed.service.article.dto.ArticleDTOHelper;
import com.project.newsfeed.service.user.dto.UserDTOHelper;

public class UpvoteDTOHelper {

    public UpvoteDTOHelper() {
    }

    public static UpvoteDTO fromEntity(Upvote upvote) {
        UpvoteDTO upvoteDTO = new UpvoteDTO();
        upvoteDTO.setId(upvote.getId());
        upvoteDTO.setUserDTO(UserDTOHelper.fromEntity(upvote.getUser()));
        upvoteDTO.setArticleDTO(ArticleDTOHelper.fromEntity(upvote.getArticle()));

        return upvoteDTO;
    }

    public static Upvote toEntity(UpvoteDTO upvoteDTO) {
        Upvote upvote = new Upvote();
        upvote.setId(upvoteDTO.getId());
        upvote.setUser(UserDTOHelper.toEntity(upvoteDTO.getUserDTO()));
        upvote.setArticle(ArticleDTOHelper.toEntity(upvoteDTO.getArticleDTO()));

        return upvote;
    }


}
