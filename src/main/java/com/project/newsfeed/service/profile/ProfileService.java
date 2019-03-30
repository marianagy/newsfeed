package com.project.newsfeed.service.profile;

import com.project.newsfeed.entity.profile.Profile;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.profile.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {
    List<ProfileDTO> findAll();

    ProfileDTO findById(int id) throws BusinessException;

    void save(Profile profile) throws BusinessException;

    void deleteById(int id);

    ProfileDTO getProfileByUser(String username);
}
