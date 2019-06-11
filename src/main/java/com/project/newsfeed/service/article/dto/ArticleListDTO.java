package com.project.newsfeed.service.article.dto;

import java.util.List;

public class ArticleListDTO {

    private List<ArticleDTO> articleDTOList;
    private Integer amount;

    public ArticleListDTO(List<ArticleDTO> articleDTOList, Integer amount) {
        this.articleDTOList = articleDTOList;
        this.amount = amount;
    }

    public List<ArticleDTO> getArticleDTOList() {
        return articleDTOList;
    }

    public void setArticleDTOList(List<ArticleDTO> articleDTOList) {
        this.articleDTOList = articleDTOList;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
