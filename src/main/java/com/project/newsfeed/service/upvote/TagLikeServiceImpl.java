package com.project.newsfeed.service.upvote;

import com.project.newsfeed.dao.upvote.TagLikeDAO;
import com.project.newsfeed.entity.article.Tag;
import com.project.newsfeed.entity.upvote.TagLike;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.upvote.dto.TagLikeDTO;
import com.project.newsfeed.service.upvote.dto.TagLikeDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagLikeServiceImpl implements TagLikeService {

    private TagLikeDAO tagLikeDAO;

    @Autowired
    public TagLikeServiceImpl(TagLikeDAO tagLikeDAO) {
        this.tagLikeDAO = tagLikeDAO;
    }

    @Override
    public List<TagLikeDTO> findAll() {
        return tagLikeDAO.findAll().stream()
                .map(TagLikeDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TagLikeDTO findById(int id) throws BusinessException {
        Optional<TagLike> result = tagLikeDAO.findById(id);
        if (result.isPresent()) {

            return TagLikeDTOHelper.fromEntity(result.get());
        } else {
            throw new BusinessException(ExceptionCode.TAGLIKE_NOT_FOUND);
        }
    }

    /**
     * Method saves tagId, userId, and amount of likes for each tag, from each user
     *
     * @param tagList
     * @param userId
     * @throws BusinessException
     */
    @Override
    public void save(List<Tag> tagList, Integer userId) throws BusinessException {

        // Get list of TagLike that user already liked
        List<TagLike> tagLikeList = tagLikeDAO.findTagLikeByUserId(userId);

        // Get list of ids from tagList (list of tags from liked article)
        List<Integer> intTagList = tagList.stream()
                .map(Tag::getId)
                .collect(Collectors.toList());

        // for each tagLike, if the user has liked the tag before => like+1 and remove id from intTagList
        tagLikeList.forEach(tagLike -> {
            if (intTagList.contains(tagLike.getTagId())) {

                tagLike.setLike(tagLike.getLike() + 1);
                intTagList.remove(tagLike.getTagId());
            }
        });

        // add a new tagList for the tags that have not been liked before
        if (!intTagList.isEmpty()) {

            intTagList.forEach(tagId -> {
                TagLike tagLike = new TagLike();
                tagLike.setUserId(userId);
                tagLike.setTagId(tagId);
                tagLike.setLike(1);

                tagLikeDAO.save(tagLike);
            });

        }


    }


    @Override
    public void deleteById(Integer id) {
        tagLikeDAO.deleteById(id);
    }

    /**
     * Method finds tags and likes for user, and sorts the result by number of likes
     *
     * @param userId
     * @return
     */
    @Override
    public List<TagLikeDTO> findTagLikeByUserId(Integer userId) {
        List<TagLikeDTO> tagLikeList = tagLikeDAO.findTagLikeByUserId(userId)
                .stream()
                .map(TagLikeDTOHelper::fromEntity)
                .sorted(Comparator.comparing(TagLikeDTO::getLike).reversed())
                .collect(Collectors.toList());
        return tagLikeList;

    }
}
