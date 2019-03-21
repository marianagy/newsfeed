package com.project.newsfeed.dao.user;

import com.project.newsfeed.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    // Todo: get roles for user
}
