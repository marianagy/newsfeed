package com.project.newsfeed.rest.article;

import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.ArticleService;
import com.project.newsfeed.service.article.CommentService;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.article.dto.CommentDTO;
import com.project.newsfeed.service.user.UserService;
import com.project.newsfeed.service.user.dto.UserDTOHelper;
import com.project.newsfeed.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CommentRestController {

    private CommentService commentService;
    private UserService userService;
    private ArticleService articleService;

    @Autowired
    public CommentRestController(CommentService commentService, UserService userService, ArticleService articleService) {
        this.commentService = commentService;
        this.userService = userService;
        this.articleService = articleService;
    }

    @RequestMapping(value = "/comments",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<CommentDTO>> findAll() {
        return ResponseEntity.ok().body(commentService.findAll());
    }

    @RequestMapping(value = "/comments/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable int id) throws BusinessException {
        CommentDTO commentDTO = commentService.findById(id);
        if (commentDTO == null) {
            throw new RuntimeException("Comment id not found - " + commentDTO);
        }
        return ResponseEntity.ok().body(commentDTO);
    }

    @RequestMapping(value = "/get-article-comments/{articleId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<CommentDTO>> getCommentsForArticle(
            @PathVariable String articleId) {
        ArticleDTO article = null;
        try {
            article = articleService.findById(Integer.parseInt(articleId));

        } catch (BusinessException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(commentService.getCommentsForArticle(article));
    }


    @RequestMapping(value = "/save-comment",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity addComment(@RequestBody CommentDTO commentDTO,
                                     @RequestHeader("Authorization") String token) {
        try {

            User requester = userService.getUserByUsername(RequestUtils.getRequesterUsername(token));
            commentDTO.setUser(UserDTOHelper.fromEntity(requester));
            commentService.save(commentDTO);
        } catch (BusinessException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(commentDTO);
    }

    @RequestMapping(value = "/update-comment",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity updateComment(@RequestBody CommentDTO commentDTO,
                                        @RequestHeader("Authorization") String token) {


        try {
            User requester = userService.getUserByUsername(RequestUtils.getRequesterUsername(token));
            commentDTO.setUser(UserDTOHelper.fromEntity(requester));
            commentService.save(commentDTO);
        } catch (BusinessException e) {

            return ResponseEntity.badRequest().body(e.getExceptionCode().getMessage());
        }

        return ResponseEntity.ok().body(commentDTO);
    }

    @RequestMapping(value = "/delete-comment/{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity deleteComment(@PathVariable int id) throws BusinessException {

        CommentDTO tempCommentDTO = commentService.findById(id);

        // throw exception if null
        if (tempCommentDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        commentService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
