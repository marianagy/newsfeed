package com.project.newsfeed.service.upvote.dto;

import java.util.Objects;

public class TagLikeDTO {

    private Integer id;
    private Integer userId;
    private Integer tagId;
    private Integer like;

    public TagLikeDTO() {


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagLikeDTO that = (TagLikeDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(tagId, that.tagId) &&
                Objects.equals(like, that.like);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, tagId, like);
    }

    @Override
    public String toString() {
        return "TagLikeDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", tagId=" + tagId +
                ", like=" + like +
                '}';
    }
}
