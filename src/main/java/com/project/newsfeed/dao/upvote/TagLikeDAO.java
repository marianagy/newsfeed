package com.project.newsfeed.dao.upvote;

import com.project.newsfeed.entity.upvote.TagLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagLikeDAO extends JpaRepository<TagLike, Integer> {

    @Query("Select t FROM TagLike t where t.userId=?1")
    public List<TagLike> findTagLikeByUserId(Integer userId);


}
