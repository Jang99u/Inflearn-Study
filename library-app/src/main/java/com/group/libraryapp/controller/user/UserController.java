package com.group.libraryapp.controller.user;

import com.group.libraryapp.controller.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final List<User> users = new ArrayList<>();

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        users.add(new User(userCreateRequest.getName(), userCreateRequest.getAge()));
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        List<UserResponse> responses = new ArrayList<>();
        for(int i = 0; i < users.size(); i++) {
            responses.add(new UserResponse(i+1, users.get(i).getName(), users.get(i).getAge()));
        }
        return responses;
    }
}
