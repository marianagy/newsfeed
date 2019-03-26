package com.project.newsfeed.rest.user;

import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.user.UserService;
import com.project.newsfeed.service.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserRestController{

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService=userService;
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
        if(user==null){
            throw new RuntimeException("User id not found - " + userId);
        }
        return user;
    }

    // add mapping for POST /users - add new user
    @PostMapping("/users")
    public User addUser(@RequestBody User user){


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
    public Optional<User> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }


}


