package com.hdsoft.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hdsoft.database.db;
import com.hdsoft.repo.school;

@Controller
public class viewController {

    @Autowired
    private db dbImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
      
        return "home/index";
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@ModelAttribute school detail, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        String res = dbImpl.insertUser(detail);

        if ("success".equals(res)) {
            return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Student added successfully!\"}");
        }
        return ResponseEntity.status(401).body("{\"status\": \"failed\", \"message\": \"Failed to add student.\"}");
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/details")
    public ResponseEntity<List<school>> details() {
        List<school> sc = jdbcTemplate.query("SELECT * FROM school", new BeanPropertyRowMapper<>(school.class));
        return ResponseEntity.ok(sc);
    }
    
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id ){
    	
    	String sql = "DELETE FROM school WHERE 	id = ?";
    	int rowsAffected = jdbcTemplate.update(sql,id);
    	
    	if(rowsAffected > 0) {
    		return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Student deleted successfully!\"}");
    	}
    	 return ResponseEntity.status(401).body("{\"status\": \"failed\", \"message\": \"Failed to delete student.\"}");
    	
    }
}
