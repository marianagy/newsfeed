package com.project.newsfeed.service.user;

import com.project.newsfeed.dao.user.ProfileDAO;
import com.project.newsfeed.entity.user.Profile;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileDAO profileDAO;

    @Autowired
    public ProfileServiceImpl(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Override
    public List<Profile> findAll() {

        return profileDAO.findAll();
    }

    @Override
    public Profile findById(int id) throws BusinessException {
        Optional<Profile> result = profileDAO.findById(id);
        if (result.isPresent()) {

            return result.get();
        } else {
            throw new BusinessException(ExceptionCode.PROFILE_DOES_NOT_EXIST);
        }
    }

    @Override
    public void save(Profile profile) {
        profileDAO.save(profile);
    }

    @Override
    public void deleteById(int id) {
        profileDAO.deleteById(id);
    }

    public Profile findByEmail(String email) {
        return profileDAO.findbyEmail(email);
    }
}
