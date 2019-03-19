package com.project.newsfeed.rest.user;

import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController

public class AuthenticationRestController {

    @Autowired
    private UserServiceImpl userService;

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<User> authenticateUser(@RequestParam(value = "username") String username,
                                                 @RequestParam(value = "password") String password) {
        User user = null;
        try {
            user = userService.loginUser(username, password);

            return ResponseEntity.accepted().body(user);
        } catch (BusinessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getExceptionCode().getMessage(), e);
        }

    }

//    @RequestMapping("/login")
//    public User authenticateUser(@RequestParam(value = "username") String username,
//                                                 @RequestParam(value = "password") String password) {
//        User user=null;
//        try {
//            user = userService.loginUser(username,password);
//
//            return user;
//        } catch (BusinessException e) {
//
//            return null;
//        }
//
//    }


}
