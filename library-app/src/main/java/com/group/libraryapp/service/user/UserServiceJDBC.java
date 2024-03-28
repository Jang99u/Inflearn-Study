package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJDBC {
    private final UserJdbcRepository userJdbcRepository;
    public UserServiceJDBC(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest userCreateRequest) {
        userJdbcRepository.saveUser(userCreateRequest.getName(), userCreateRequest.getAge());
    }

    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest userUpdateRequest) {
        if (userJdbcRepository.isUserNotExist(userUpdateRequest.getId())) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUserName(userUpdateRequest.getName(), userUpdateRequest.getId());
    }

    public void deleteUser(String name) {
        if (userJdbcRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.deleteUserByName(name);
    }

}
