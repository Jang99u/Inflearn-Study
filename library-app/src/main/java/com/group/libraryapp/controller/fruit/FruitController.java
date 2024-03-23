package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitUpdateRequest;
import com.group.libraryapp.dto.fruit.response.FruitSaleResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;

    public FruitController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
