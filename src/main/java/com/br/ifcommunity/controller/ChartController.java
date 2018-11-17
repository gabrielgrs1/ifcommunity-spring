package com.br.ifcommunity.controller;

import com.br.ifcommunity.dao.ChartDAO;
import com.br.ifcommunity.model.Chart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")
public class ChartController {

    @RequestMapping(value = "/charts", method = RequestMethod.GET)
    public ResponseEntity getChartsInfo(@RequestParam String userId) {
        ArrayList<Chart> chartsInfoList;

        try {
            chartsInfoList = ChartDAO.getChartsInfo(userId);

            if (chartsInfoList == null) {
                chartsInfoList = (ArrayList<Chart>) Collections.singletonList(new Chart("Falha ao buscar informações do gráfico!"));
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(chartsInfoList);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));

        }

        return ResponseEntity.ok().body(chartsInfoList);
    }
}
