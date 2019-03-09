package com.project.newsfeed.service.user;

import com.project.newsfeed.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();

    public User findById(int id);

    public void save(User user);

    public void deleteById(int id);

    public Optional<User> getUserByUsername(String username);



}
