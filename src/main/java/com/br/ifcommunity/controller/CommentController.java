package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.CommentDAO;
import com.br.ifcommunity.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/comment")
public class CommentController {
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity comment(@RequestBody Comment comment) {
        ArrayList<String> returnMessage = new ArrayList<>();

        try {
            returnMessage.add(CommentDAO.comment(comment));

            if (returnMessage.get(0).equals("Erro desconhecido!")) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(returnMessage);
            }

            return ResponseEntity.ok().body(returnMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity comment(@RequestParam int postId) {
        ArrayList<Comment> commentList;

        try {
            commentList = CommentDAO.getComments(postId);

            if (commentList == null) {
                commentList = (ArrayList<Comment>) Collections.singletonList(
                        new Comment("Falha ao buscar os comentários, ou nenhum comentário foi encontrado!"));
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commentList);
            }
            return ResponseEntity.ok().body(commentList);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));

        }

    }
}
