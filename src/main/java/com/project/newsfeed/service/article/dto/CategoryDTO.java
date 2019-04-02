package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.entity.article.Article;

import java.util.List;
import java.util.Objects;

public class CategoryDTO {

    private Integer id;
    private String name;
    private List<Article> categoryArticleList;

    public CategoryDTO() {
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
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(categoryArticleList, that.categoryArticleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryArticleList);
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryArticleList=" + categoryArticleList +
                '}';
    }
}
