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

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")
public class ChartController {

    @RequestMapping(value = "/charts", method = RequestMethod.GET)
    public ResponseEntity<Chart> getChartsInfo(@RequestParam int userId) {
        Chart chartsInfo = null;

        try {
            chartsInfo = ChartDAO.getChartsInfo(userId);

            if (chartsInfo == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Chart("Falha ao buscar informações do gráfico!"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(chartsInfo);
    }
}
