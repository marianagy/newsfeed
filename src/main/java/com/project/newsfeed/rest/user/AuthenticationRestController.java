package com.project.newsfeed.rest.user;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.user.UserServiceImpl;
import com.project.newsfeed.service.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class AuthenticationRestController {


    private UserServiceImpl userService;

    @Autowired
    public AuthenticationRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody MultiValueMap<String, String> paramMap) {
        UserDTO userDTO = null;
        try {
            if (paramMap.get("username") == null || paramMap.get("password") == null) {
                throw new BusinessException(ExceptionCode.UNKNOWN_EXCEPTION);
                //todo : fa un exception, null params
            }
            String username = paramMap.getFirst("username");
            String password = paramMap.getFirst("password");
            userDTO = userService.loginUser(username, password);

            return ResponseEntity.accepted().body(userDTO);
        } catch (BusinessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getExceptionCode().getMessage(), e);
        }

    }



}
