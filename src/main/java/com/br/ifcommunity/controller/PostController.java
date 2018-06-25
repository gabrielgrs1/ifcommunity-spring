package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.PostDAO;
import com.br.ifcommunity.model.LikeDeslikePost;
import com.br.ifcommunity.model.Post;
import com.br.ifcommunity.model.PostEdited;
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
@RequestMapping(value = "/post")
@CrossOrigin(origins = "*")
public class PostController {
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Post>> getPost(@RequestParam String name, String lastPost) {
        ArrayList<Post> listPost = null;

        try {
            listPost = PostDAO.getPost(name, lastPost);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(Objects.requireNonNull(listPost));
    }

    @RequestMapping(value = "/like", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ArrayList<String>> likeDeslike(@RequestBody LikeDeslikePost likeStructure) {
        ArrayList<String> returnMessage = new ArrayList<>();


        try {
            returnMessage.add(PostDAO.likeDeslike(likeStructure));

            if (returnMessage.get(0).equals("Erro desconhecido!")) {
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(returnMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(returnMessage);
    }


    @RequestMapping(value = "/make", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> doPost(@RequestBody Post postRequestBody) {
        try {
            PostDAO.addPost(postRequestBody);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ArrayList<String>> editPost(@RequestBody PostEdited postRequestBody) {
        ArrayList<String> returnMessage = new ArrayList<>();

        try {
            returnMessage.add(PostDAO.editPost(postRequestBody));

            if (returnMessage.get(0).equals("Usuário não tem permissão para editar essa postagem!")) {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(returnMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(returnMessage);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ArrayList<String>> removePost(@RequestBody PostEdited postRequestBody) {
        ArrayList<String> returnMessage = new ArrayList<>();

        try {
            returnMessage.add(PostDAO.removePost(postRequestBody));

            if (returnMessage.get(0).equals("Usuário não tem permissão para remover essa postagem!")) {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(returnMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(returnMessage);
    }


}
