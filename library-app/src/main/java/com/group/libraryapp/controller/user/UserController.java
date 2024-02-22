package com.group.libraryapp.controller.user;

import com.group.libraryapp.controller.domain.user.User;
import com.group.libraryapp.dto.user.request.FruitCreateRequest;
import com.group.libraryapp.dto.user.request.FruitUpdateRequest;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.FruitSaleResponse;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final List<User> users = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        String sql = "insert into user (name, age) values (?, ?)";
        jdbcTemplate.update(sql, userCreateRequest.getName(), userCreateRequest.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        String readSql = "SELECT * FROM user WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, userUpdateRequest.getId()).isEmpty();
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, userUpdateRequest.getName(), userUpdateRequest.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        String readSql = "SELECT * FROM user WHERE name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    @PostMapping("/api/v1/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest fruitCreateRequest) {
        String sql = "INSERT INTO fruit (name, price, stocked_date, sold) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, fruitCreateRequest.getName(),
                fruitCreateRequest.getPrice(),
                fruitCreateRequest.getWarehousingDate(),
                false);
    }

    @PutMapping("/api/v1/fruit")
    public void updateFruit(@RequestBody FruitUpdateRequest fruitUpdateRequest){
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, fruitUpdateRequest.getId()).isEmpty();
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE fruit SET sold = ? WHERE id = ?";
        jdbcTemplate.update(sql, true, fruitUpdateRequest.getId());
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitSaleResponse howManySale(@RequestParam String name) {
        String readSql = "SELECT * FROM fruit WHERE name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sqlSold = "SELECT * FROM fruit WHERE name = ? AND sold = 1";
        String sqlNotSold = "SELECT * FROM fruit WHERE name = ? AND sold = 0";

        List<Integer> sqlSoldList = jdbcTemplate.query(sqlSold, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("price");
            }
        }, name);

        List<Integer> sqlNotSoldList = jdbcTemplate.query(sqlNotSold, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("price");
            }
        }, name);

        Integer salePrice = 0;
        for (int i = 0; i < sqlSoldList.size(); i++) {
            salePrice += sqlSoldList.get(i);
        }

        Integer notSalePrice = 0;
        for (int i = 0; i < sqlNotSoldList.size(); i++) {
            notSalePrice += sqlNotSoldList.get(i);
        }

        return new FruitSaleResponse(salePrice, notSalePrice);
    }
}
