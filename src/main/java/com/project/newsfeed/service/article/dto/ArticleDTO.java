package com.project.newsfeed.service.article.dto;

import com.project.newsfeed.service.profile.dto.ProfileDTO;
import com.project.newsfeed.service.user.dto.UserDTO;

import java.util.List;
import java.util.Objects;

public class ArticleDTO {

    private Integer id;
    private String title;
    private String content;
    private String image;
    private UserDTO user;
    private Integer nrUpvotes;
    private List<String> tagList;
    private List<String> categoryList;
    private ProfileDTO profileDTO;

    public ArticleDTO() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getNrUpvotes() {
        return nrUpvotes;
    }

    public void setNrUpvotes(Integer nrUpvotes) {
        this.nrUpvotes = nrUpvotes;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public ProfileDTO getProfileDTO() {
        return profileDTO;
    }

    public void setProfileDTO(ProfileDTO profileDTO) {
        this.profileDTO = profileDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDTO that = (ArticleDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(image, that.image) &&
                Objects.equals(user, that.user) &&
                Objects.equals(nrUpvotes, that.nrUpvotes) &&
                Objects.equals(tagList, that.tagList) &&
                Objects.equals(categoryList, that.categoryList) &&
                Objects.equals(profileDTO, that.profileDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, image, user, nrUpvotes, tagList, categoryList, profileDTO);
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", user=" + user +
                ", nrUpvotes=" + nrUpvotes +
                ", tagList=" + tagList +
                ", categoryList=" + categoryList +
                ", profileDTO=" + profileDTO +
                '}';
    }
}
