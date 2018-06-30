package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.UserDAO;
import com.br.ifcommunity.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody User user) {
        System.out.println("[REGISTRO] User passado pelo front: " + user);

        try {
            if (UserDAO.verifyRegister(user).equals("")) {
                user = UserDAO.register(user);

                return ResponseEntity.ok().body(user);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public ResponseEntity login(@RequestBody User user) {
        System.out.println("[LOGIN] User passado pelo front: " + user);

        try {
            user = UserDAO.login(user);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonList("Usuário ou senha incorreto!"));
            }

            return ResponseEntity.ok().body(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity udpateUser(@RequestBody User user) {
        System.out.println("[ATUALIZA USUARIO] User passado pelo front: " + user);

        try {
            user = UserDAO.updateStudent(user);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok().body(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity verifyIsNotRegister(@RequestParam String verifyString) {
        String verifyError;

        try {
            verifyError = UserDAO.verifyIsNotRegister(verifyString);

            if (verifyError.equals("Pode cadastrar!")) {
                return ResponseEntity.ok().body(Collections.singletonList(verifyError));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonList(verifyError));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }
}





