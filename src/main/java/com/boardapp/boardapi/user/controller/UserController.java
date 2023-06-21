package com.boardapp.boardapi.user.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boardapp.boardapi.user.model.UserDto;
import com.boardapp.boardapi.user.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<UserDto> findAllUser() {
        return this.userService.getAllUser();
    }

    @GetMapping("/:{userId}")
    private UserDto findUserById(@PathVariable String userId) {
        return this.userService.getUserById(userId);
    }

    @PostMapping
    private void createUser(@RequestBody UserDto userDto) {
        this.userService.saveUser(userDto);
    }

    @PutMapping("/:{userId}")
    private void modifyUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        this.userService.modifyUser(userId, userDto);
    }

    @DeleteMapping("/:{userId}")
    private void deleteUser(@PathVariable String userId) {
        this.userService.deleteUser(userId);
    }
}
