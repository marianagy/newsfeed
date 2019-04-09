package com.project.newsfeed.rest.upvote;

import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.ArticleService;
import com.project.newsfeed.service.article.dto.ArticleDTO;
import com.project.newsfeed.service.upvote.UpvoteService;
import com.project.newsfeed.service.upvote.dto.UpvoteDTO;
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
public class UpvoteRestController {

    private UpvoteService upvoteService;
    private UserService userService;
    private ArticleService articleService;

    @Autowired
    public UpvoteRestController(UpvoteService upvoteService, UserService userService, ArticleService articleService) {
        this.upvoteService = upvoteService;
        this.userService = userService;
        this.articleService = articleService;
    }

    @RequestMapping(value = "/upvotes",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<UpvoteDTO>> findAll() {
        return ResponseEntity.ok().body(upvoteService.findAll());
    }

    @RequestMapping(value = "/upvotes/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<UpvoteDTO> getArticleById(@PathVariable int id) throws BusinessException {
        UpvoteDTO upvoteDTO = upvoteService.findById(id);
        if (upvoteDTO == null) {
            throw new RuntimeException("Upvote id not found - " + upvoteDTO);
        }
        return ResponseEntity.ok().body(upvoteDTO);
    }

    @RequestMapping(value = "/save-upvote",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity addUpvote(@RequestParam("article_id") String articleID,
                                    @RequestHeader("Authorization") String token) {
        UpvoteDTO upvoteDTO = new UpvoteDTO();

        try {
            ArticleDTO article = articleService.findById(Integer.parseInt(articleID));
            upvoteDTO.setArticleDTO(article);
            User requester = userService.getUserByUsername(RequestUtils.getRequesterUsername(token));

            upvoteDTO.setUserDTO(UserDTOHelper.fromEntity(requester));
            upvoteService.save(upvoteDTO);
        } catch (BusinessException e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
        return ResponseEntity.ok().body(upvoteDTO);
    }

    @RequestMapping(value = "/get-article-upvotes",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<Integer> getUpvoteNrForArticle(@RequestParam Integer articleID) {
        Integer nuOfUpvotes = upvoteService.getUpvoteNrForArticle(articleID);

        return ResponseEntity.ok().body(nuOfUpvotes);
    }

    @RequestMapping(value = "/user-upvoted/{articleID}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<String> userHasUpvotedArticle(@PathVariable int articleID,
                                                        @RequestHeader("Authorization") String token) {

        try {
            ArticleDTO articleDTO = articleService.findById(articleID);
            User requester = userService.getUserByUsername(RequestUtils.getRequesterUsername(token));
            Boolean userHasUpvotedArt = upvoteService.userHasUpvotedArticle(requester, articleDTO);
            return ResponseEntity.ok().body(userHasUpvotedArt.toString());
        } catch (BusinessException e) {
            return ResponseEntity.ok().body(e.getMessage());
        }

    }

    @RequestMapping(value = "/remove-upvote",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity removeUpvote(@RequestParam("article_id") String articleID,
                                       @RequestHeader("Authorization") String token) {


        try {
            ArticleDTO article = articleService.findById(Integer.parseInt(articleID));

            User requester = userService.getUserByUsername(RequestUtils.getRequesterUsername(token));

            upvoteService.deleteUpvote(requester, article);
        } catch (BusinessException e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
        return ResponseEntity.ok().body(articleID);
    }


}
