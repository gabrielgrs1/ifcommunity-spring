package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.UserDAO;
import com.br.ifcommunity.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("[REGISTRO] User passado pelo front: " + user);

        try {
            if (UserDAO.verifyRegister(user).equals("")) {
                user = UserDAO.register(user);

                return ResponseEntity.ok().body(user);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public ResponseEntity<User> login(@RequestBody User user) {
        System.out.println("[LOGIN] User passado pelo front: " + user);

        try {
            user = UserDAO.login(user);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> udpateUser(@RequestBody User user) {
        System.out.println("[ATUALIZA USUARIO] User passado pelo front: " + user);

        try {
            user = UserDAO.updateStudent(user);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok().body(user);
            }
        } catch (SQLException e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> verifyIsNotRegister(@RequestParam String verifyString) {
        String verifyError;

        try {
            verifyError = UserDAO.verifyIsNotRegister(verifyString);
            return ResponseEntity.ok().body(verifyError);

        } catch (SQLException e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/photo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadPhoto(@RequestBody User user) {
        int isSucess;
        try {
            isSucess = UserDAO.uploadPhoto(user);

            if (isSucess == 0) {
                return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            } else {
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (SQLException e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}





