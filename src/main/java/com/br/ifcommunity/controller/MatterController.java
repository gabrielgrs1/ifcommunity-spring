package com.br.ifcommunity.controller;


import com.br.ifcommunity.dao.MatterDAO;
import com.br.ifcommunity.model.Matter;
import com.br.ifcommunity.model.MatterUser;
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
@RequestMapping(value = "/matter")
public class MatterController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMatters() {
        ArrayList<Matter> matterlist = null;

        try {
            matterlist = MatterDAO.getAllMatters();

            if (matterlist.size() == 0) {
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(matterlist.add(new Matter(0, "ERRO AO BUSCAR MATERIAS", 0)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }

        return ResponseEntity.ok().body(matterlist);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMatterById(@RequestParam int studentId) {
        ArrayList<Matter> matterlist;

        try {
            matterlist = MatterDAO.getUserMatters(studentId);

            if (matterlist.size() == 0) {
                ResponseEntity.status(HttpStatus.CONFLICT).body(matterlist.add(new Matter(0, "Erro ao buscar matérias ou não foi encontrada nenhuma!", 0)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }

        return ResponseEntity.ok().body(matterlist);
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMattersUser(@RequestBody MatterUser responseBody) {
        String resultString;
        try {
            resultString = MatterDAO.updateMattersUser(responseBody);

            if (resultString.equals("Matérias atualizadas com sucesso!")) {
                return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(resultString));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(resultString));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }
}
