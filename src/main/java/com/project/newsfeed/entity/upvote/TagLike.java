package com.project.newsfeed.entity.upvote;


import javax.persistence.*;
import java.util.Objects;

/**
 * POJO for tracking which user liked which tags and how many times
 */
@Entity
@Table(name = "tag_like")
public class TagLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "likes")
    private Integer like;

    public TagLike() {
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
        TagLike tagLike = (TagLike) o;
        return Objects.equals(id, tagLike.id) &&
                Objects.equals(userId, tagLike.userId) &&
                Objects.equals(tagId, tagLike.tagId) &&
                Objects.equals(like, tagLike.like);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, tagId, like);
    }

    @Override
    public String toString() {
        return "TagLike{" +
                "id=" + id +
                ", userId=" + userId +
                ", tagId=" + tagId +
                ", like=" + like +
                '}';
    }
}
