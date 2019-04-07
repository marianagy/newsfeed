package com.project.newsfeed.service.user;

import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    User findById(int id) throws BusinessException;

    void save(User user);

    void deleteById(int id);

    User getUserByUsername(String username) throws BusinessException;



}
