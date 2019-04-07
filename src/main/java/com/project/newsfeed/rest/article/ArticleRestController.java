package com.project.newsfeed.rest.article;

import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.ArticleService;
import com.project.newsfeed.service.article.dto.ArticleDTO;
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
public class ArticleRestController {

    private ArticleService articleService;
    private UserService userService;

    @Autowired
    public ArticleRestController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/articles",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<ArticleDTO>> findAll() {
        return ResponseEntity.ok().body(articleService.findAll());
    }

    @RequestMapping(value = "/articles/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable int id) throws BusinessException {
        ArticleDTO articleDTO = articleService.findById(id);
        if (articleDTO == null) {
            throw new RuntimeException("Article id not found - " + articleDTO);
        }
        return ResponseEntity.ok().body(articleDTO);
    }

    @RequestMapping(value = "/save-article",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<ArticleDTO> addArticle(@RequestBody ArticleDTO articleDTO,
                                                 @RequestHeader("Authorization") String token) {

        try {
            User requester = userService.getUserByUsername(RequestUtils.getRequesterUsername(token));

            articleDTO.setUser(UserDTOHelper.fromEntity(requester));
            articleService.save(articleDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(articleDTO);
    }



    @RequestMapping(value = "/update-article",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO) {


        try {
            articleService.save(articleDTO);
        } catch (BusinessException e) {
            //todo : fa-l bine
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(articleDTO);
    }

    @RequestMapping(value = "/delete-article/{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity deleteArticle(@PathVariable int id) throws BusinessException {

        ArticleDTO tempArticleDTO = articleService.findById(id);

        // throw exception if null
        if (tempArticleDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        articleService.deleteById(id);

        return ResponseEntity.ok().build();
    }


}
