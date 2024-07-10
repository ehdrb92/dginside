package com.donggyu.dginside;

import com.donggyu.dginside.user.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUserSuccess() {
        UserJoinDTO inputUserJoinDTO = new UserJoinDTO();

        inputUserJoinDTO.setUsername("testUsername");
        inputUserJoinDTO.setPassword("testPassword");
        inputUserJoinDTO.setName("testName");
        inputUserJoinDTO.setEmail("testUsername@email.com");
        inputUserJoinDTO.setNickname("testNickname");

        User resultUser = new User();

        resultUser.setUsername("testUsername");
        resultUser.setName("testName");
        resultUser.setEmail("testUsername@email.com");
        resultUser.setNickname("testNickname");

        when(userService.createUser(inputUserJoinDTO)).thenReturn(resultUser);
        Assertions.assertEquals(userController.createUser(inputUserJoinDTO), new ResponseEntity<>(resultUser, HttpStatus.CREATED));
    }

    @Test
    public void testGetUser() {
        User resultUser = new User();

        resultUser.setId(1L);

        when(userService.getUser(1L)).thenReturn(resultUser);
        Assertions.assertEquals(userController.getUser(1L), new ResponseEntity<>(resultUser, HttpStatus.OK));
    }

    @Test
    public void testUpdateUser() {
        User userToUpdate = new User();

        userToUpdate.setId(1L);

        Optional<User> resultFindById = Optional.of(userToUpdate);

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("tester@email.com");

        when(userService.updateUser(1L, userUpdateDTO)).thenReturn(true);
        Assertions.assertEquals(userController.updateUser(1L, userUpdateDTO), new ResponseEntity<>(true, HttpStatus.OK));
    }

    @Test
    public void testDeleteUser() {
        Assertions.assertEquals(userController.deleteUser(1L), new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
