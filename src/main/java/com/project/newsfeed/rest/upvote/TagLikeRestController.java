package com.project.newsfeed.rest.upvote;


import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.upvote.TagLikeService;
import com.project.newsfeed.service.upvote.dto.TagLikeDTO;
import com.project.newsfeed.service.user.UserService;
import com.project.newsfeed.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TagLikeRestController {

    private TagLikeService tagLikeService;
    private UserService userService;

    @Autowired
    public TagLikeRestController(TagLikeService tagLikeService) {
        this.tagLikeService = tagLikeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/get-taglike",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<TagLikeDTO>> userHasUpvotedArticle(@RequestHeader("Authorization") String token) {
        User requester = null;
        try {

            requester = userService.getUserByUsername(RequestUtils.getRequesterUsername(token));


        } catch (BusinessException e) {
            e.printStackTrace();
        }
        assert requester != null;
        return ResponseEntity.ok().body(tagLikeService.findTagLikeByUserId(requester.getId()));


    }
}
