package com.project.newsfeed.service.article;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.dto.TagDTO;

import java.util.List;

public interface TagService {

    List<TagDTO> findAll();

    TagDTO findById(int id) throws BusinessException;

    void save(TagDTO profile) throws BusinessException;

    void deleteById(int id);
}
