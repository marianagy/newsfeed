package com.project.newsfeed.rest.user;

import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.user.UserService;
import com.project.newsfeed.service.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // expose "/users" and return list of users
    @GetMapping("/users")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    // add mapping for GET /users/{userId}
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable int userId) throws BusinessException {
        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User id not found - " + userId);
        }
        return user;
    }

    // add mapping for POST /users - add new user
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {


        userService.save(user);
        return user;
    }

    // add mapping for PUT /users - update existing yser

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {


        userService.save(user);

        return user;
    }

    // add mapping for DELETE /users/{userId} - delete user

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) throws BusinessException {

        User tempUser = userService.findById(userId);

        // throw exception if null

        if (tempUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }

        userService.deleteById(userId);

        return "Deleted user id - " + userId;
    }

    @GetMapping("/get-user-by-username/{username}")
    public User getUserByUsername(@PathVariable String username) throws BusinessException {
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/deactivate-user",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    public ResponseEntity<Object> deactivateUser(@RequestBody MultiValueMap<String, String> paramMap) {
        String username = paramMap.getFirst("username");
        try {
            userService.deactivateUser(username);
            return ResponseEntity.ok().build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getExceptionCode().getMessage());
        }


    }

    @RequestMapping(value = "/activate-user",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    public ResponseEntity<Object> activateUser(@RequestBody MultiValueMap<String, String> paramMap) {

        try {
            userService.activateUser(paramMap.getFirst("username"));

            return ResponseEntity.ok().build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getExceptionCode().getMessage());

        }


    }

    @RequestMapping(value = "/is-user-active/{username}",
            method = RequestMethod.GET

    )
    @ResponseBody
    public Boolean isUserActive(@PathVariable String username) {

        return userService.isActive(username);


    }
}


