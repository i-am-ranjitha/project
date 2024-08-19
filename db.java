package com.hdsoft.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hdsoft.repo.school;

@Repository
public class db {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String insertUser(school user) {
        String sql = "INSERT INTO school (name, std) VALUES (?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getName(), user.getSTD());

        return rowsAffected > 0 ? "success" : "";
    }
}
