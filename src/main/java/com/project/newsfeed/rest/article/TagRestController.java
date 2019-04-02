package com.project.newsfeed.rest.article;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.TagService;
import com.project.newsfeed.service.article.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TagRestController {

    private TagService tagService;

    @Autowired
    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "/tags",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<TagDTO>> findAll() {
        return ResponseEntity.ok().body(tagService.findAll());
    }

    @RequestMapping(value = "/tags/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<TagDTO> getTagById(@PathVariable int id) throws BusinessException {
        TagDTO tagDTO = tagService.findById(id);
        if (tagDTO == null) {
            throw new RuntimeException("Tag id not found - " + tagDTO);
        }
        return ResponseEntity.ok().body(tagDTO);
    }

    @RequestMapping(value = "/save-tag",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<TagDTO> addTag(@RequestBody TagDTO tagDTO) {


        try {
            tagService.save(tagDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(tagDTO);
    }

    @RequestMapping(value = "/update-tag",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<TagDTO> updateTag(@RequestBody TagDTO tagDTO) {


        try {
            tagService.save(tagDTO);
        } catch (BusinessException e) {
            //todo : fa-l bine
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(tagDTO);
    }

    @RequestMapping(value = "/delete-tag/{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity deleteTag(@PathVariable int id) throws BusinessException {

        TagDTO tempTagDTO = tagService.findById(id);

        // throw exception if null
        if (tempTagDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        tagService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
