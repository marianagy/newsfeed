package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.service.user.dto.UserDTO;

import java.util.Date;
import java.util.Objects;

public class CommentDTO {

    private Integer id;
    private String content;
    private Date date;
    private UserDTO user;
    private ArticleDTO article;

    public CommentDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(content, that.content) &&
                Objects.equals(date, that.date) &&
                Objects.equals(user, that.user) &&
                Objects.equals(article, that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, date, user, article);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", article=" + article +
                '}';
    }
}
