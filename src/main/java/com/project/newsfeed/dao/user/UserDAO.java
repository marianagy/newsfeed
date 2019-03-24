package com.project.newsfeed.dao.user;

import com.project.newsfeed.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<User, Integer> {

    @Query("Select u FROM User u where username=?1")
    User findByUsername(String username);


    // Todo: get roles for user
}
