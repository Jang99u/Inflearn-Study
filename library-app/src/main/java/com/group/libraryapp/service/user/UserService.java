package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserService {

    // 이런건 private final이 맞지
    private final UserRepository userRepository = new UserRepository();

    public void updateUser(JdbcTemplate jdbcTemplate, UserUpdateRequest userUpdateRequest) {
        if (userRepository.isUserNotExist(jdbcTemplate, userUpdateRequest.getId())) {
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(jdbcTemplate, userUpdateRequest.getName(), userUpdateRequest.getId());
    }

}
