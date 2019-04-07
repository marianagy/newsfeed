package com.project.newsfeed.entity.upvote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.newsfeed.entity.article.Article;
import com.project.newsfeed.entity.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "upvote")
public class Upvote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private Article article;

//    public Upvote(User user, Article article) {
//        this.user = user;
//        this.article = article;
//    }

    public Upvote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Upvote upvote = (Upvote) o;
        return Objects.equals(id, upvote.id) &&
                Objects.equals(user, upvote.user) &&
                Objects.equals(article, upvote.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, article);
    }

    @Override
    public String toString() {
        return "Upvote{" +
                "id=" + id +
                ", user=" + user +
                ", article=" + article +
                '}';
    }
}
