package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceJPA;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceJPA userServiceJPA;
    public UserController(UserServiceJPA userServiceJdbc) {
        this.userServiceJPA = userServiceJdbc;
    }

    @PostMapping
    public void saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        userServiceJPA.saveUser(userCreateRequest);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userServiceJPA.getUsers();
    }

    @PutMapping
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        userServiceJPA.updateUser(userUpdateRequest);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam String name) {
        userServiceJPA.deleteUser(name);
    }

}
