package com.project.newsfeed.dao.user;

import com.project.newsfeed.entity.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDAO extends JpaRepository<Profile, Integer> {

}
