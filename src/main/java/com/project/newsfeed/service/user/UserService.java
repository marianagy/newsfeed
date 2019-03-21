package com.project.newsfeed.service.user;

import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.user.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserDTO> findAll();

    public User findById(int id) throws BusinessException;

    public void save(User user);

    public void deleteById(int id);

    public Optional<User> getUserByUsername(String username);



}
