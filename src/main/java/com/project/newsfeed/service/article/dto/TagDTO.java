package com.project.newsfeed.service.article.dto;

import java.util.Objects;

public class TagDTO {

    private Integer id;
    private String name;
//    private List<Article> taggedArticleList;

    public TagDTO() {
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
//
//    public List<Article> getTaggedArticleList() {
//        return taggedArticleList;
//    }
//
//    public void setTaggedArticleList(List<Article> taggedArticleList) {
//        this.taggedArticleList = taggedArticleList;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDTO tagDTO = (TagDTO) o;
        return Objects.equals(id, tagDTO.id) &&
                Objects.equals(name, tagDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
