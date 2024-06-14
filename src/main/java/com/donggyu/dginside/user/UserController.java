package com.donggyu.dginside.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    @PostMapping(path = "/join")
    public ResponseEntity<?> createUser(@RequestBody UserJoinDTO userJoinDTO) {
        return new ResponseEntity<>(userService.createUser(userJoinDTO), HttpStatus.CREATED);
    }

    // TODO: 로그인 API 추가 필요

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
