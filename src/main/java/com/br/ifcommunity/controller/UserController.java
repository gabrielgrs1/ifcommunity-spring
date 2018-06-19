package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.UserDAO;
import com.br.ifcommunity.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        if (user.getTypeUser() == 1) { // Case the user is Student
            try {
                if (UserDAO.verifyRegister(user).equals("")) {
                    user = UserDAO.register(user);
                } else {
                    user = new User("Usuário ou email ou matricula já cadastro(s)!");

                    return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (user.getTypeUser() == 2) { // Case the user is Teacher

        } else { // Case the user is Administrator

        }


        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> login(@RequestBody User requestBody) {
        User user = null;

        try {
            user = UserDAO.login(requestBody);
            System.out.println("User response body " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> udpateUser(@RequestBody User requestBody) {
        User user = null;

        try {
            user = UserDAO.updateStudent(requestBody); // Case update Student

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(user);
    }
}

