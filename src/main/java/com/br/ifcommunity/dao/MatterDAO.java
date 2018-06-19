package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.Matter;
import com.br.ifcommunity.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatterDAO {

    public static ArrayList<Matter> getAllMatters() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Matter> mattersList = new ArrayList<>();

        String SQLQuery = "SELECT * FROM TB_MATERIA";
        preparedStatement = connection.prepareStatement(SQLQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            mattersList.add(new Matter(
                    resultSet.getInt("ID"),
                    resultSet.getString("NOME_MATERIA"),
                    resultSet.getInt("PERIODO")
            ));
        }

        connection.close();
        return mattersList;
    }

    public static ArrayList<Matter> getUserMatters(int idUser) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Matter> mattersList = new ArrayList<>();

        String SQLQuery = "SELECT * FROM TB_MATERIA_ALUNO AS TB_MA" +
                " INNER JOIN TB_MATERIA AS TB_M ON (TB_M.ID = TB_MA.ID_MATERIA)" +
                " WHERE ID_ALUNO = ?";

        preparedStatement = connection.prepareStatement(SQLQuery);
        preparedStatement.setInt(1, idUser);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            mattersList.add(new Matter(
                    resultSet.getInt("TB_M.ID"),
                    resultSet.getString("NOME_MATERIA"),
                    resultSet.getInt("PERIODO")
            ));
        }

        connection.close();
        return mattersList;
    }
}
