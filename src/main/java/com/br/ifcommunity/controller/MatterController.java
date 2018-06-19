package com.br.ifcommunity.controller;


import com.br.ifcommunity.dao.MatterDAO;
import com.br.ifcommunity.model.Matter;
import com.br.ifcommunity.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.ArrayList;


@Controller
@RequestMapping(value = "/matter")
public class MatterController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Matter>> getAllMatters() {
        ArrayList<Matter> matterlist = null;

        try {
            matterlist = MatterDAO.getAllMatters();

            if (matterlist.size() == 0) {
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(matterlist.add(new Matter(0,"ERRO AO BUSCA MATERIAS", 0)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(matterlist);
    }
}
