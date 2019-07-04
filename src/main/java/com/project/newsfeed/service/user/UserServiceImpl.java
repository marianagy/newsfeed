package com.project.newsfeed.service.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.project.newsfeed.dao.profile.ProfileDAO;
import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.profile.Profile;
import com.project.newsfeed.entity.user.Role;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.service.user.dto.UserDTO;
import com.project.newsfeed.service.user.dto.UserDTOHelper;
import com.project.newsfeed.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;


    private ProfileDAO profileDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, ProfileDAO profileDAO) {
        this.userDAO = userDAO;
        this.profileDAO = profileDAO;
    }

    /**
     * Get all users
     *
     * @return
     */
    @Override
    public List<UserDTO> findAll() {

        return userDAO.findAll().stream()
                .map(UserDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Get user by id
     *
     * @param id
     * @return
     */
    @Override
    public User findById(int id) throws BusinessException {
        Optional<User> result = userDAO.findById(id);
        if (result.isPresent()) {

            return result.get();
        } else {
            throw new BusinessException(ExceptionCode.USER_WITH_ID_NOT_FOUND);
        }
    }

    /**
     * Save user - encrypt password and set default flag to true (means user is activated), set default role to user
     *
     * @param user
     */
    @Override
    public void save(User user) {

        Role role = new Role();
        role.setId(2);
        role.setName("user");
        user.setRole(role);
        user.setFlag(true);
        user.setPassword(Encryptor.encrypt(user.getPassword()));
        userDAO.save(user);
    }

    /**
     * Delete user
     *
     * @param id
     */
    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    /**
     * Get all users that have the same role
     *
     * @param role
     * @return
     */
    public List<User> getAllUsersWithRole(Role role) {
        return userDAO.findAll()
                .stream()
                .filter(user -> user.getRole().equals(role))
                .collect(Collectors.toList());
    }

    /**
     * Check if email is valid using regex
     *
     * @param email
     * @return
     */
    public boolean isEmailValid(String email) {
        final Pattern validEmailAddressRegex =
                Pattern.compile("^[a-zA-Z][A-Za-z0-9._]*@[a-zA-z]+.[a-z]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = validEmailAddressRegex.matcher(email);
        return matcher.find();
    }

    public User getUserByUsername(String username) throws BusinessException {

        return userDAO.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ExceptionCode.USERNAME_NOT_FOUND));


    }

    public void activateUser(String username) throws BusinessException {
        User user = getUserByUsername(username);
        user.setFlag(true);
        userDAO.save(user);

    }

    public void deactivateUser(String username) throws BusinessException {
        User user = getUserByUsername(username);
        user.setFlag(false);
        userDAO.save(user);

    }

    private boolean usernameExist(String username) {
        Optional<User> userOptional = userDAO.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        return userOptional.isPresent();
    }

    public void registerUser(String firstName, String lastName, String email, String username, String password) throws BusinessException {

        if (usernameExist(username)) {
            throw new BusinessException(ExceptionCode.USERNAME_ALREADY_EXISTS);
        }
        if (!isEmailValid(email)) {
            throw new BusinessException(ExceptionCode.EMAIL_VALIDATION_EXCEPTION);
        }
        if (profileDAO.findbyEmail(email) != null) {
            throw new BusinessException(ExceptionCode.EMAIL_EXISTS_ALREADY);
        }


        Profile profile = new Profile();
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setEmail(email);


        profileDAO.save(profile);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setProfile(profile);
        save(user);



    }

    /**
     * Takes the username and password of a user and if they are correct, it returns the
     * corresponding user. Otherwise it will throw an exception.
     *
     * @param username
     * @param password
     * @return
     * @throws BusinessException
     */
    public String loginUser(String username, String password) throws BusinessException {
        User user = getUserByUsername(username);
        if (username == null) {
            throw new BusinessException(ExceptionCode.USERNAME_NOT_FOUND);
        }
        if (!user.getFlag()) {
            throw new BusinessException(ExceptionCode.USER_DEACTIVATED);
        }
        //String passwordDB=userOptional.get().getPassword();
        //password = Encryptor.encrypt(password);
        if (!Encryptor.encrypt(password).equals(user.getPassword())) {

            throw new BusinessException(ExceptionCode.PASSWORD_NOT_VALID);
        }
        return issueToken(user);
    }

    private boolean validateFields(User user) {
        return
                user.getUsername() != null;
    }

    private boolean isValidForCreation(User user) throws BusinessException {
        //validate if email already exists
        if (userDAO.findByUsername(user.getUsername()) != null) {

            throw new BusinessException(ExceptionCode.USERNAME_ALREADY_EXISTS);
        }
        return validateFields(user)
                && user.getPassword() != null;
    }

    /**
     * Validates the user. To use before sending it further.
     *
     * @param user
     * @throws BusinessException
     */
    private void validateUserForCreation(User user) throws BusinessException {
        if (!isValidForCreation(user)) {
            throw new BusinessException(ExceptionCode.USER_VALIDATION_EXCEPTION);
        }


    }

    private String issueToken(User user) {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Bucharest"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        Date out = Date.from(tomorrowMidnight.atZone(ZoneId.systemDefault()).toInstant());


        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return "{ \"token\" : \"" + JWT.create()
                    .withIssuer(user.getUsername())
                    .withExpiresAt(out)
                    .withClaim("username", user.getUsername())
                    .withClaim("role", user.getRole().getName())
                    .sign(algorithm) + "\" }";

        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            //log.catching(exception);
            return "{token: ''}";
        }

    }

    /**
     * Method returns true is user is active and false if not
     *
     * @param username
     * @return
     */
    public Boolean isActive(String username) {

        User user = userDAO.findByUsername(username);
        return user.getFlag();

    }


}
