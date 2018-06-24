package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.CommentDAO;
import com.br.ifcommunity.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/post")
public class CommentController {
    @RequestMapping(value = "/comment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> comment(@RequestBody Comment comment) {
        int isSucess = 0;
        try {
            isSucess = CommentDAO.comment(comment);

            if (isSucess == 0) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(true);
    }
}
