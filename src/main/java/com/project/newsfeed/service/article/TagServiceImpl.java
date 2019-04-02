package com.project.newsfeed.service.article;

import com.project.newsfeed.dao.article.TagDAO;
import com.project.newsfeed.entity.article.Tag;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.article.dto.TagDTO;
import com.project.newsfeed.service.article.dto.TagDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    TagDAO tagDAO;

    @Autowired
    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public List<TagDTO> findAll() {
        return tagDAO.findAll().stream()
                .map(TagDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO findById(int id) throws BusinessException {
        Optional<Tag> result = tagDAO.findById(id);
        if (result.isPresent()) {

            return TagDTOHelper.fromEntity(result.get());
        } else {
            throw new BusinessException(ExceptionCode.TAG_NOT_FOUND);
        }
    }

    @Override
    public void save(TagDTO tagDTO) throws BusinessException {
        tagDAO.save(TagDTOHelper.toEntity(tagDTO));
    }

    @Override
    public void deleteById(int id) {
        tagDAO.deleteById(id);
    }
}
