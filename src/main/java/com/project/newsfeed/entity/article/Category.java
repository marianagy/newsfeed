package com.project.newsfeed.entity.article;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "categoryList")
    private List<Article> categoryArticleList;

    public Category() {
    }

    public Category(@NotNull @Size(max = 100) String name, List<Article> categoryArticleList) {
        this.name = name;
        this.categoryArticleList = categoryArticleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getCategoryArticleList() {
        return categoryArticleList;
    }

    public void setCategoryArticleList(List<Article> categoryArticleList) {
        this.categoryArticleList = categoryArticleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(categoryArticleList, category.categoryArticleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryArticleList);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryArticleList=" + categoryArticleList +
                '}';
    }
}
