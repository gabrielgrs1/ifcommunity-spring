package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.Chart;
import com.br.ifcommunity.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ChartDAO {
    public static ArrayList<Chart> getChartsInfo(int userId) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Chart> chartList = new ArrayList<>();
        Chart chart = null;
        int like = 0;
        int deslike = 0;


        String SQLQuery = "SELECT TB_POSTAGEM.LINGUAGEM_POSTAGEM, TB_MATERIA.NOME_MATERIA,"
                + " COUNT(LINGUAGEM_POSTAGEM) as CONTAGEM_POSTAGEM FROM TB_POSTAGEM"
                + " INNER JOIN TB_MATERIA ON (TB_POSTAGEM.ID_MATERIA = TB_MATERIA.ID)"
                + " WHERE ID_USUARIO = ? "
                + " GROUP BY TB_POSTAGEM.LINGUAGEM_POSTAGEM, TB_MATERIA.NOME_MATERIA";

        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            chart = new Chart(resultSet.getString("LINGUAGEM_POSTAGEM"),
                    resultSet.getInt("CONTAGEM_POSTAGEM"),
                    resultSet.getString("NOME_MATERIA")
            );

            chartList.add(chart);
        }

        SQLQuery = "SELECT * FROM TB_CONTAGEM_LIKE " +
                " INNER JOIN TB_POSTAGEM TP on TB_CONTAGEM_LIKE.ID_POSTAGEM = TP.ID"
                + " WHERE TP.ID_USUARIO = ? ";

        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt("LIKE_DESLIKE") == 0) {
                like++;
            } else {
                deslike++;
            }
        }

        SQLQuery = "SELECT * FROM TB_CONTAGEM_LIKE " +
                " INNER JOIN TB_POSTAGEM TP on TB_CONTAGEM_LIKE.ID_POSTAGEM = TP.ID" +
                " INNER JOIN TB_COMENTARIO_POSTAGEM TC on TP.ID = TC.ID_POSTAGEM"
                + " WHERE TP.ID_USUARIO = ? ";

        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt("LIKE_DESLIKE") == 0) {
                like++;
            } else {
                deslike++;
            }
        }

        chart = new Chart(like, deslike);
        chartList.add(chart);

        connection.close();

        return chartList;
    }
}

class main {
    public static void main(String[] args) {
        try {
            System.out.println(ChartDAO.getChartsInfo(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
