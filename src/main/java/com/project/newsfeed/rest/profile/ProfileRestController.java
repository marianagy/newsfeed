package com.project.newsfeed.rest.profile;

import com.project.newsfeed.entity.profile.Profile;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProfileRestController {

    private ProfileService profileService;

    @Autowired
    public ProfileRestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // expose "/profiles" and return list of profiles

    @GetMapping("/profiles")
    public List<Profile> findAll() {
        return profileService.findAll();
    }

    @GetMapping("/profiles/{id}")
    public Profile getProfileById(@PathVariable int profileId) throws BusinessException {
        Profile profile = profileService.findById(profileId);
        if (profile == null) {
            throw new RuntimeException("Profile id not found - " + profile);
        }
        return profile;
    }

    @PostMapping("/save-profile")
    public Profile addProfile(@RequestBody Profile profile) {


        profileService.save(profile);
        return profile;
    }

    //    @PutMapping("/update-profile")
    @RequestMapping(value = "/update-profile",
            method = RequestMethod.PUT
    )
    public Profile updateProfile(@RequestBody Profile profile) {


        profileService.save(profile);

        return profile;
    }

    @DeleteMapping("/profile/{profileId}")
    public String deleteProfile(@PathVariable int profileId) throws BusinessException {

        Profile tempProfile = profileService.findById(profileId);

        // throw exception if null

        if (tempProfile == null) {
            throw new RuntimeException("Profile id not found - " + profileId);
        }

        profileService.deleteById(profileId);

        return "Deleted profile id - " + profileId;
    }


    @RequestMapping(value = "/current-profile/{username}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Profile getProfileByUser(@PathVariable("username") String username) {
        return profileService.getProfileByUser(username);
    }

}
