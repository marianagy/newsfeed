package com.project.newsfeed.service.profile;

import com.project.newsfeed.dao.profile.ProfileDAO;
import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.profile.Profile;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileDAO profileDAO;
    private UserDAO userDAO;

    @Autowired
    public ProfileServiceImpl(ProfileDAO profileDAO, UserDAO userDAO) {
        this.profileDAO = profileDAO;
        this.userDAO = userDAO;
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

    /**
     * Method gets profiles of a certain user
     *
     * @param username
     * @return
     */
    public Profile getProfileByUser(String username) {
        User user = userDAO.findByUsername(username);
        return user.getProfile();
    }
}
