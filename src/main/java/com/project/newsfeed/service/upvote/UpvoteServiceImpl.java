package com.project.newsfeed.service.upvote;

import com.project.newsfeed.dao.upvote.UpvoteDAO;
import com.project.newsfeed.entity.upvote.Upvote;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.upvote.dto.UpvoteDTO;
import com.project.newsfeed.service.upvote.dto.UpvoteDTOHelper;
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


        upvoteDAO.save(UpvoteDTOHelper.toEntity(upvoteDTO));
    }

    @Override
    public void deleteById(int id) {
        upvoteDAO.deleteById(id);
    }

    @Override
    public Integer getUpvoteNrForArticle(Integer articleID) {
        return upvoteDAO.getUpvoteNrForArticle(articleID);
    }


}
