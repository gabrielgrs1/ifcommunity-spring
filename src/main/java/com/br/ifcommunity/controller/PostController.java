package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.PostDAO;
import com.br.ifcommunity.model.LikeDeslikePost;
import com.br.ifcommunity.model.Post;
import com.br.ifcommunity.model.PostEdited;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

@Controller
@RequestMapping(value = "/post")
@CrossOrigin(origins = "*")
public class PostController {
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getPost(@RequestParam String name, String lastPost) {
        ArrayList<Post> listPost = null;

        try {
            listPost = PostDAO.getPost(name, lastPost);
            return ResponseEntity.ok().body(Objects.requireNonNull(listPost));
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }

    }

    @RequestMapping(value = "/like", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity likeDeslike(@RequestBody LikeDeslikePost likeStructure) {
        ArrayList<String> returnMessage = new ArrayList<>();

        try {
            returnMessage.add(PostDAO.likeDeslike(likeStructure));

            if (returnMessage.get(0).equals("Erro desconhecido!")) {
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(returnMessage);
            }

            return ResponseEntity.ok().body(returnMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }

    }


    @RequestMapping(value = "/make", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity doPost(@RequestBody Post postRequestBody) {
        ArrayList<String> returnMessage = new ArrayList<>();

        try {
            returnMessage.add(PostDAO.addPost(postRequestBody));

            if (returnMessage.get(0).equals("Postagem inserida com sucesso!")) {
                return ResponseEntity.ok().body(returnMessage);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity editPost(@RequestBody PostEdited postRequestBody) {
        ArrayList<String> returnMessage = new ArrayList<>();

        try {
            returnMessage.add(PostDAO.editPost(postRequestBody));

            if (returnMessage.get(0).equals("Usuário não tem permissão para editar essa postagem!")) {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(returnMessage);
            }

            return ResponseEntity.ok().body(returnMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity removePost(@RequestBody PostEdited postRequestBody) {
        ArrayList<String> returnMessage = new ArrayList<>();

        try {
            returnMessage.add(PostDAO.removePost(postRequestBody));

            if (returnMessage.get(0).equals("Usuário não tem permissão para remover essa postagem!")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(returnMessage);
            }

            return ResponseEntity.ok().body(returnMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity searchPost(@RequestParam @NonNull String searchKeyword,  @NonNull String matter) {
        ArrayList<Post> postArrayList;

        try {
            postArrayList = PostDAO.searchPost(searchKeyword, matter);

            if (postArrayList.size() == 0) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.singletonList("Não foi encontrada nenhuma postagem!"));
            }

            return ResponseEntity.ok().body(postArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }


}
