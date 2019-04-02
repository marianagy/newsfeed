package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.entity.article.Tag;

public class TagDTOHelper {

    public TagDTOHelper() {
    }

    public static TagDTO fromEntity(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        tagDTO.setTaggedArticleList(tag.getTaggedArticleList());

        return tagDTO;
    }

    public static Tag toEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tag.getName());
        tag.setTaggedArticleList(tagDTO.getTaggedArticleList());

        return tag;
    }
}
