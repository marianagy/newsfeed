package com.project.newsfeed.rest.article;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.CommentService;
import com.project.newsfeed.service.article.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CommentRestController {

    private CommentService commentService;

    @Autowired
    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
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

    @RequestMapping(value = "/save-comment",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {


        try {
            commentService.save(commentDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(commentDTO);
    }

    @RequestMapping(value = "/update-comment",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO) {


        try {
            commentService.save(commentDTO);
        } catch (BusinessException e) {
            //todo : fa-l bine
            e.printStackTrace();
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
