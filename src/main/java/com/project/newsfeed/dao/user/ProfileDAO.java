package com.project.newsfeed.dao.user;

import com.project.newsfeed.entity.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileDAO extends JpaRepository<Profile, Integer> {

    @Query("Select p From Profile p where p.email=?1")
    Profile findbyEmail(String email);
}
