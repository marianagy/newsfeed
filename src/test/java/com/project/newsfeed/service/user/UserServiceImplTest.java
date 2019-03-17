package com.project.newsfeed.service.user;

import com.project.newsfeed.dao.user.UserDAO;
import com.project.newsfeed.entity.user.User;
import com.project.newsfeed.exception.BusinessException;
import com.project.newsfeed.exception.ExceptionCode;
import com.project.newsfeed.utils.Encryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    @Test
    public void findAll() {
        User user = new User();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userDAO.findAll()).thenReturn(userList);

        assertEquals(1,userService.findAll().size());
    }

    @Test
    public void findById_ExpectedSuccess() throws BusinessException {
        User user = new User();
        user.setId(1);
        when(userDAO.findById(1)).thenReturn(Optional.of(user));
        assertEquals(user,userService.findById(1));
    }

    @Test
    public void findById_ExpectedUserNotFound() {
        User user = new User();
        user.setId(1);
        when(userDAO.findById(1)).thenReturn(Optional.of(user));
        try{userService.findById(2);
            fail("Should not reach this point");
        } catch (BusinessException e) {
           assertEquals(ExceptionCode.USER_WITH_ID_NOT_FOUND,e.getExceptionCode());
        }
    }

    @Test
    public void getAllUsersWithRole() {
    }

    @Test
    public void isEmailValid() {
    }

    @Test
    public void getUserByUsername() {
    }

    @Test
    public void activateUser() {
    }

    @Test
    public void deactivateUser() {
    }

    @Test
    public void registerUser() {
    }

    @Test
    public void loginUser_ExpectedSuccess() {
        User user = new User();
        user.setUsername("usernamujhfgv");
        user.setPassword(Encryptor.encrypt("passwordjhf"));
        user.setFlag(true);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        when(userDAO.findAll()).thenReturn(users);

        try {
            assertEquals(user,userService.loginUser("usernamujhfgv","passwordjhf"));
        } catch (BusinessException e) {
            e.printStackTrace();
            fail("Should not reach this point");
        }
    }

    @Test
    public void loginUser_ExpectedUsernameNotFound() {
        User user = new User();
        user.setUsername("bla");
        user.setPassword(Encryptor.encrypt("passwordjhf"));
        user.setFlag(true);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        when(userDAO.findAll()).thenReturn(users);

        try {
            userService.loginUser("usernamujhfgv","passwordjhf");
            fail("Should not reach this point");
        } catch (BusinessException e) {
            assertEquals(ExceptionCode.USERNAME_NOT_FOUND,e.getExceptionCode());
        }
    }

    @Test
    public void loginUser_ExpectedPasswordNotValid() {
        User user = new User();
        user.setUsername("usernamujhfgv");
        user.setPassword(Encryptor.encrypt("dfe"));
        user.setFlag(true);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        when(userDAO.findAll()).thenReturn(users);

        try {
            userService.loginUser("usernamujhfgv","passwordjhf");
            fail("Should not reach this point");
        } catch (BusinessException e) {
            assertEquals(ExceptionCode.PASSWORD_NOT_VALID,e.getExceptionCode());
        }
    }

    @Test
    public void loginUser_ExpectedUserDeactivated() {
        User user = new User();
        user.setUsername("usernamujhfgv");
        user.setPassword(Encryptor.encrypt("passwordjhf"));
        user.setFlag(false);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        when(userDAO.findAll()).thenReturn(users);

        try {
            userService.loginUser("usernamujhfgv","passwordjhf");
            fail("Should not reach this point");
        } catch (BusinessException e) {
            assertEquals(ExceptionCode.USER_DEACTIVATED,e.getExceptionCode());
        }
    }
}