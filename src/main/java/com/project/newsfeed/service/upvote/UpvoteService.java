package com.project.newsfeed.service.upvote;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.upvote.dto.UpvoteDTO;

import java.util.List;

public interface UpvoteService {

    List<UpvoteDTO> findAll();

    UpvoteDTO findById(int id) throws BusinessException;

    void save(UpvoteDTO upvoteDTO) throws BusinessException;

    void deleteById(int id);

    Integer getUpvoteNrForArticle(Integer articleID);
}
