package com.project.newsfeed.entity.article;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "category")
public class Category {

    @ManyToMany(mappedBy = "categoryList")
    List<Article> categoryArticleList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Size(max = 100)
    @Column(name = "name")
    private String name;


}
