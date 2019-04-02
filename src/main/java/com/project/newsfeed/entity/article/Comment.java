package com.project.newsfeed.entity.article;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.newsfeed.entity.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    // @Size()
    @Column(name = "content")
    private String content;

    @NotNull
    // @Size()
    @Column(name = "date")
    @DateTimeFormat
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private Article article;

}
