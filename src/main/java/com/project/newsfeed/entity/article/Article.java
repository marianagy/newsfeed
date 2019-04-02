package com.project.newsfeed.entity.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.newsfeed.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "article")
public class Article {

    @ManyToMany
    @JoinTable(
            name = "article_tag", // name of many-to-many table
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    List<Tag> tagList;
    @ManyToMany
    @JoinTable(
            name = "article_category", // name of many-to-many table
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categoryList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Size(max = 150)
    @Column(name = "title")
    private String title;
    @NotNull
    // @Size()
    @Column(name = "content")
    private String content;
    @Column(name = "image")
    @Lob
    private byte[] image;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private User user;
    @Column(name = "nr_upvotes")
    private Integer nrUpvotes;


}
