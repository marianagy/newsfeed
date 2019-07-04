package com.project.newsfeed.rest.article;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.article.CategoryService;
import com.project.newsfeed.service.article.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/categories",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @RequestMapping(value = "/categories/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<CategoryDTO> getCategoriesById(@PathVariable int id) throws BusinessException {
        CategoryDTO categoryDTO = categoryService.findById(id);
        if (categoryDTO == null) {
            throw new RuntimeException("Category id not found - " + categoryDTO);
        }
        return ResponseEntity.ok().body(categoryDTO);
    }

    @RequestMapping(value = "/save-category",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {


        try {
            categoryService.save(categoryDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(categoryDTO);
    }

    @RequestMapping(value = "/update-category",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<Object> updateCategory(@RequestBody CategoryDTO categoryDTO) {


        try {
            categoryService.save(categoryDTO);
        } catch (BusinessException e) {

            return ResponseEntity.badRequest().body(e.getExceptionCode().getMessage());
        }

        return ResponseEntity.ok().body(categoryDTO);
    }

    @RequestMapping(value = "/delete-category/{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity deleteCategory(@PathVariable int id) throws BusinessException {

        CategoryDTO tempCategoryDTO = categoryService.findById(id);

        // throw exception if null
        if (tempCategoryDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        categoryService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
