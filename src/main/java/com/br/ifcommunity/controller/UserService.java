package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.StudentDAO;
import com.br.ifcommunity.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/user")
public class UserService {

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        try {
            if (user.getTypeUser() == 1) { // Case the user is Student
                if (StudentDAO.verifyRegister(user).equals("")) {
                    user = StudentDAO.register(user);
                }
            } else if (user.getTypeUser() == 2) { // Case the user is Teacher

            } else { // Case the user is Administrator

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(user);
    }
}
