package com.project.newsfeed.service.upvote;

import com.project.newsfeed.entity.article.Tag;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.upvote.dto.TagLikeDTO;

import java.util.List;

public interface TagLikeService {

    List<TagLikeDTO> findAll();

    TagLikeDTO findById(int id) throws BusinessException;

    void save(List<Tag> tagList, Integer userId) throws BusinessException;

    void deleteById(Integer id);

    List<TagLikeDTO> findTagLikeByUserId(Integer userId);

}
