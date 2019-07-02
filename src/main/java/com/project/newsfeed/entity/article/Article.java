package com.project.newsfeed.entity.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.newsfeed.entity.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "article")
public class Article {

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private User user;

    @Column(name = "nr_upvotes")
    private Integer nrUpvotes;

    @ManyToMany
    @JoinTable(
            name = "article_tag", // name of many-to-many table
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tagList;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "article_category", // name of many-to-many table
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList;

    public Article() {
    }

    public Article(@NotNull @Size(max = 150) String title, @NotNull String content, byte[] image, User user, Integer nrUpvotes, List<Tag> tagList, List<Category> categoryList) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.user = user;
        this.nrUpvotes = nrUpvotes;
        this.tagList = tagList;
        this.categoryList = categoryList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNrUpvotes() {
        return nrUpvotes;
    }

    public void setNrUpvotes(Integer nrUpvotes) {
        this.nrUpvotes = nrUpvotes;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(title, article.title) &&
                Objects.equals(content, article.content) &&
                Arrays.equals(image, article.image) &&
                Objects.equals(user, article.user) &&
                Objects.equals(nrUpvotes, article.nrUpvotes) &&
                Objects.equals(tagList, article.tagList) &&
                Objects.equals(categoryList, article.categoryList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, content, user, nrUpvotes, tagList, categoryList);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image=" + Arrays.toString(image) +
                ", user=" + user +
                ", nrUpvotes=" + nrUpvotes +
                ", tagList=" + tagList +
                ", categoryList=" + categoryList +
                '}';
    }
}
