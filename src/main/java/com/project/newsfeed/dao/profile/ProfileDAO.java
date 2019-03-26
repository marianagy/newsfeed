package com.project.newsfeed.dao.profile;

import com.project.newsfeed.entity.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileDAO extends JpaRepository<Profile, Integer> {

    @Query("Select p From Profile p where p.email=?1")
    Profile findbyEmail(String email);


}
