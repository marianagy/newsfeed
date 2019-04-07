package com.project.newsfeed.service.upvote.dto;

import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.user.dto.UserDTO;

import java.util.Objects;

public class UpvoteDTO {

    private Integer id;
    private UserDTO userDTO;
    private ArticleDTO articleDTO;

    public UpvoteDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpvoteDTO upvoteDTO = (UpvoteDTO) o;
        return Objects.equals(id, upvoteDTO.id) &&
                Objects.equals(userDTO, upvoteDTO.userDTO) &&
                Objects.equals(articleDTO, upvoteDTO.articleDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userDTO, articleDTO);
    }

    @Override
    public String toString() {
        return "UpvoteDTOHelper{" +
                "id=" + id +
                ", userDTO=" + userDTO +
                ", articleDTO=" + articleDTO +
                '}';
    }
}
