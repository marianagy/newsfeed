package com.project.newsfeed.service.profile;

import com.project.newsfeed.dao.profile.ProfileDAO;
import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.profile.Profile;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.profile.dto.ProfileDTO;
import com.project.newsfeed.service.profile.dto.ProfileDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ProfileDTO> findAll() {

        return profileDAO.findAll().stream()
                .map(ProfileDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDTO findById(int id) throws BusinessException {
        Optional<Profile> result = profileDAO.findById(id);
        if (result.isPresent()) {

            return ProfileDTOHelper.fromEntity(result.get());
        } else {
            throw new BusinessException(ExceptionCode.PROFILE_DOES_NOT_EXIST);
        }
    }

    @Override
    public void save(Profile profile) throws BusinessException {
        validateProfileInput(profile);
        profileDAO.save(profile);
    }

    private void validateProfileInput(Profile profile) throws BusinessException {
        if (profile.getFirstName() == null || profile.getFirstName().trim().equals("")) {
            throw new BusinessException(ExceptionCode.FIELD_VALUE_IS_NULL);
        }
        if (profile.getLastName() == null || profile.getLastName().trim().equals("")) {
            throw new BusinessException(ExceptionCode.FIELD_VALUE_IS_NULL);
        }
        if (profile.getEmail() == null || profile.getEmail().trim().equals("")) {
            throw new BusinessException(ExceptionCode.FIELD_VALUE_IS_NULL);
        }
        if (profileDAO.findbyEmail(profile.getEmail()) != null) {
            throw new BusinessException(ExceptionCode.EMAIL_EXISTS_ALREADY);
        }
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
    public ProfileDTO getProfileByUser(String username) {
        User user = userDAO.findByUsername(username);
        return ProfileDTOHelper.fromEntity(user.getProfile());
    }
}
