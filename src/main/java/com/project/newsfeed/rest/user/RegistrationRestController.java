package com.project.newsfeed.rest.user;


import com.project.newsfeed.entity.profile.Profile;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class RegistrationRestController {

    private UserServiceImpl userService;

    @Autowired
    public RegistrationRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public ResponseEntity<Profile> registerUser(@RequestParam("first_name") String firstname,
                                                @RequestParam("last_name") String lastName,
                                                @RequestParam("username") String username,
                                                @RequestParam("password") String password,
                                                @RequestParam("email") String email
    ) {
        try {
            Profile profile = null;

            if (firstname == null || lastName == null || username == null || password == null || email == null) {
                throw new BusinessException(ExceptionCode.FIELD_VALUE_IS_NULL);
            }

            userService.registerUser(firstname, lastName, email, username, password);
            return ResponseEntity.ok().body(profile);
        } catch (BusinessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getExceptionCode().getMessage(), e);

        }
    }
}
