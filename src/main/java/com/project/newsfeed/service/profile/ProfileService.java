package com.project.newsfeed.service.profile;

import com.project.newsfeed.entity.profile.Profile;
import com.project.newsfeed.exception.BusinessException;

import java.util.List;

public interface ProfileService {
    List<Profile> findAll();

    Profile findById(int id) throws BusinessException;

    void save(Profile profile);

    void deleteById(int id);

    Profile getProfileByUser(String username);
}
