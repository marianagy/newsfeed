package com.project.newsfeed.service.upvote.dto;

import com.project.newsfeed.entity.upvote.TagLike;

public class TagLikeDTOHelper {

    public TagLikeDTOHelper() {
    }

    public static TagLikeDTO fromEntity(TagLike tagLike) {
        TagLikeDTO tagLikeDTO = new TagLikeDTO();
        tagLikeDTO.setUserId(tagLike.getId());
        tagLikeDTO.setUserId(tagLike.getUserId());
        tagLikeDTO.setTagId(tagLike.getTagId());
        tagLikeDTO.setLike(tagLike.getLike());

        return tagLikeDTO;
    }

    public static TagLike toEntity(TagLikeDTO tagLikeDTO) {
        TagLike tagLike = new TagLike();
        tagLike.setUserId(tagLikeDTO.getId());
        tagLike.setUserId(tagLikeDTO.getUserId());
        tagLike.setTagId(tagLikeDTO.getTagId());
        tagLike.setLike(tagLikeDTO.getLike());
        return tagLike;
    }
}
