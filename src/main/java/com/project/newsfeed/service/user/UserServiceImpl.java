package com.project.newsfeed.service.user;

import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.user.Role;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public User findById(int id) {
        Optional<User> result = userDAO.findById(id);

        User user=null;

        if(result.isPresent()){
            user=result.get(); // gives value
        }
        else{
            // we didn't find the user
            throw new RuntimeException("Did not find the user id - "+ id);
        }
        return user;

    }

    /**
     * Save user - encrypt password and set default flag to true (means user is activated)
     * @param user
     */
    @Override
    public void save(User user) {

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

}
