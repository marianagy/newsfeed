package com.project.newsfeed.entity.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tag")
public class Tag {

    @ManyToMany(mappedBy = "tagList")
    List<Article> taggedArticleList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Size(max = 100)
    @Column(name = "name")
    private String name;
}
