package com.project.newsfeed.entity.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tagList")
    private List<Article> taggedArticleList;

    public Tag() {
    }

    public Tag(@NotNull @Size(max = 100) String name, List<Article> taggedArticleList) {
        this.name = name;
        this.taggedArticleList = taggedArticleList;
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

    public List<Article> getTaggedArticleList() {
        return taggedArticleList;
    }

    public void setTaggedArticleList(List<Article> taggedArticleList) {
        this.taggedArticleList = taggedArticleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(name, tag.name) &&
                Objects.equals(taggedArticleList, tag.taggedArticleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, taggedArticleList);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taggedArticleList=" + taggedArticleList +
                '}';
    }
}
