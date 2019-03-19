package com.project.newsfeed.service.user;

import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.user.Role;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }

    /**
     * Get all users
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    /**
     * Get user by id
     * @param id
     * @return
     */
    @Override
    public User findById(int id) throws BusinessException {
        Optional<User> result = userDAO.findById(id);

        if(result.isPresent()){

            return result.get();
        }
        else{
            throw new BusinessException(ExceptionCode.USER_WITH_ID_NOT_FOUND);
        }
    }

    /**
     * Save user - encrypt password and set default flag to true (means user is activated), set default role to user
     * @param user
     */
    @Override
    public void save(User user) {

        Role role = new Role();
        role.setId(1);
        role.setName("user");
        user.setRole(role);
        user.setFlag(true);
        user.setPassword(Encryptor.encrypt(user.getPassword()));
        userDAO.save(user);
    }

    /**
     * Delete user
     * @param id
     */
    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    /**
     * Get all users that have the same role
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
     * @param email
     * @return
     */
    public boolean isEmailValid(String email) {
        final Pattern validEmailAddressRegex =
                Pattern.compile("^[a-zA-Z][A-Za-z0-9._]*@[a-zA-z]+.[a-z]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = validEmailAddressRegex.matcher(email);
        return matcher.find();
    }

//    public Optional<User> getUserByUsername(String username){
//
//        User user = new User();
//        user.setUsername(username);
//
//
//        // returns a single entity matching the given Example or Optional.empty() if none was found.
//        return userDAO.findOne(Example.of(user));
//
//    }

    public Optional<User> getUserByUsername(String username) {

        List<User> userList = userDAO.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
        if(userList.size()>0) {
            return Optional.of(userList.get(0));
        }
        else{
            return Optional.empty();
        }

    }

    public void activateUser(String username) throws BusinessException {
        Optional<User> userOptional = getUserByUsername(username);
        User user = userOptional.orElseThrow(() -> new BusinessException(ExceptionCode.USERNAME_NOT_FOUND));
        user.setFlag(true);
        userDAO.save(user);

    }

    public void deactivateUser(String username) throws BusinessException {
        Optional<User> userOptional = getUserByUsername(username);
        User user = userOptional.orElseThrow(() -> new BusinessException(ExceptionCode.USERNAME_NOT_FOUND));
        user.setFlag(false);
        userDAO.save(user);

    }

    public void registerUser(){

    }

    /**
     * Takes the username and password of a user and if they are correct, it returns the
     * corresponding user. Otherwise it will throw an exception.
     * @param username
     * @param password
     * @return
     * @throws BusinessException
     */
    public User loginUser(String username, String password) throws BusinessException {
        Optional<User> userOptional = getUserByUsername(username);
        if(!userOptional.isPresent()){
            throw new BusinessException(ExceptionCode.USERNAME_NOT_FOUND);
        }
        if (!userOptional.get().isFlag()) {
            throw new BusinessException(ExceptionCode.USER_DEACTIVATED);
        }
        //String passwordDB=userOptional.get().getPassword();
        //password = Encryptor.encrypt(password);
        if(!Encryptor.encrypt(password).equals(userOptional.get().getPassword()))      {

            throw new BusinessException(ExceptionCode.PASSWORD_NOT_VALID);
        }
        return userOptional.get();
    }



}
