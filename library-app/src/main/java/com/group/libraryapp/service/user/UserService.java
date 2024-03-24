package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest userCreateRequest) {
        userRepository.saveUser(userCreateRequest.getName(), userCreateRequest.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest userUpdateRequest) {
        if (userRepository.isUserNotExist(userUpdateRequest.getId())) {
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(userUpdateRequest.getName(), userUpdateRequest.getId());
    }

    public void deleteuser(String name) {
        if (userRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userRepository.deleteUserByName(name);
    }

}
