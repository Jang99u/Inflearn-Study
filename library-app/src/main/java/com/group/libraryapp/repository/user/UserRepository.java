package com.group.libraryapp.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepository {
    // 레포까지 내려오면 객체의 모든속성을 쓰는건 아니기 떄문에 특정 속성만 꺼내서 써도 됨
    public boolean isUserNotExist(JdbcTemplate jdbcTemplate, long id) {
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName(JdbcTemplate jdbcTemplate, String name, long id) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

}

