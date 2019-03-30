package com.project.newsfeed.service.profile.dto;

import com.project.newsfeed.entity.profile.Profile;

public class ProfileDTOHelper {

    public ProfileDTOHelper() {
    }

    public static ProfileDTO fromEntity(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setFirstName(profile.getFirstName());
        profileDTO.setLastName(profile.getLastName());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setBio(profile.getBio());
        if (profile.getProfilePicture() != null) {
            profileDTO.setPhoto(new String(profile.getProfilePicture()));
        }
        return profileDTO;
    }

    public static Profile toEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setEmail(profileDTO.getEmail());
        profile.setBio(profileDTO.getBio());
        if (profileDTO.getPhoto() != null) {
            profile.setProfilePicture(profileDTO.getPhoto().getBytes());
        }
        return profile;
    }


}
