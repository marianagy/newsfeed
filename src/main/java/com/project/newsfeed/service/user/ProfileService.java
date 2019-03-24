package com.project.newsfeed.service.user;

import com.project.newsfeed.entity.user.Profile;
import com.project.newsfeed.exception.BusinessException;

import java.util.List;

public interface ProfileService {
    public List<Profile> findAll();

    public Profile findById(int id) throws BusinessException;

    public void save(Profile profile);

    public void deleteById(int id);
}
