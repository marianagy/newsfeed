package com.project.newsfeed.rest.profile;

import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.service.profile.ProfileService;
import com.project.newsfeed.service.profile.dto.ProfileDTO;
import com.project.newsfeed.service.profile.dto.ProfileDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/profiles",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<ProfileDTO>> findAll() {
        return ResponseEntity.ok().body(profileService.findAll());
    }

    @RequestMapping(value = "/profiles/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable int profileId) throws BusinessException {
        ProfileDTO profileDTO = profileService.findById(profileId);
        if (profileDTO == null) {
            throw new RuntimeException("Profile id not found - " + profileDTO);
        }
        return ResponseEntity.ok().body(profileDTO);
    }

    @RequestMapping(value = "/save-profile",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody ProfileDTO profileDTO) {


        try {
            profileService.save(ProfileDTOHelper.toEntity(profileDTO));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(profileDTO);
    }

    //    @PutMapping("/update-profile")
    @RequestMapping(value = "/update-profile",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) {


        try {
            profileService.save(ProfileDTOHelper.toEntity(profileDTO));
        } catch (BusinessException e) {
            //todo : fa-l bine
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(profileDTO);
    }

    @DeleteMapping("/delete-profile/{profileId}")
    public String deleteProfile(@PathVariable int profileId) throws BusinessException {

        ProfileDTO tempProfileDTO = profileService.findById(profileId);

        // throw exception if null
        if (tempProfileDTO == null) {
            throw new RuntimeException("Profile id not found - " + profileId);
        }

        profileService.deleteById(profileId);

        return "Deleted profile id - " + profileId;
    }


    @RequestMapping(value = "/current-profile/{username}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<ProfileDTO> getProfileByUser(@PathVariable("username") String username) {
        return ResponseEntity.accepted().body(profileService.getProfileByUser(username));
    }

}
