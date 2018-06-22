package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.PostDAO;
import com.br.ifcommunity.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin(origins = "*")
public class PostController {
    @RequestMapping(value = "/post", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Post>> getPost(@RequestParam String name, String lastPost) {
        ArrayList<Post> listPost = null;

        try {
            listPost = PostDAO.getPost(name, lastPost);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(Objects.requireNonNull(listPost));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity.BodyBuilder doPost(@RequestBody Post postRequestBody) {
        try {
            PostDAO.addPost(postRequestBody);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK);
    }
}
