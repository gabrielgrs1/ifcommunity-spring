package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.Matter;
import com.br.ifcommunity.model.MatterUser;
import com.br.ifcommunity.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public static ArrayList<Matter> getUserMatters(int studentId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Matter> mattersList = new ArrayList<>();

        String SQLQuery = "SELECT * FROM TB_MATERIA_ALUNO AS TB_MA" +
                " INNER JOIN TB_MATERIA AS TB_M ON (TB_M.ID = TB_MA.ID_MATERIA)" +
                " WHERE ID_ALUNO = ?";

        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, studentId);
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

    public static void updateMattersUser(MatterUser responseBody) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        List<String> matterList = Arrays.asList(responseBody.getMatter1(),
                responseBody.getMatter2(),
                responseBody.getMatter3(),
                responseBody.getMatter4(),
                responseBody.getMatter5(),
                responseBody.getMatter6(),
                responseBody.getMatter7());

        System.out.println("Matérias passadas pelo front: " + matterList);

        String token = responseBody.getStudentId().split(";")[0];
        String userId = responseBody.getStudentId().split(";")[1];

        String SQLQuery = "SELECT * FROM TB_USUARIO WHERE ID = ? AND TOKEN = ?";
        preparedStatement = connection.prepareStatement(SQLQuery);
        preparedStatement.setInt(1, Integer.parseInt(userId));
        preparedStatement.setString(2, token);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            SQLQuery = "DELETE FROM TB_MATERIA_ALUNO WHERE ID_ALUNO = " +
                    "(SELECT ID FROM TB_ALUNO WHERE ID_USUARIO = ?)";
            preparedStatement = connection.prepareCall(SQLQuery);
            preparedStatement.setInt(1, Integer.parseInt(userId));
            preparedStatement.executeUpdate();

            if (matterList.get(0) != null) {
                for (int i = 0; i < matterList.size(); i++) {
                    if (matterList.get(i) != null) {
                        SQLQuery = "SELECT ID FROM TB_MATERIA WHERE NOME_MATERIA = ?";
                        preparedStatement = connection.prepareCall(SQLQuery);
                        preparedStatement.setString(1, matterList.get(i));
                        resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            SQLQuery = "INSERT INTO TB_MATERIA_ALUNO (ID_MATERIA, ID_ALUNO) " +
                                    "VALUES (?, " +
                                    "(SELECT ID FROM TB_ALUNO WHERE ID_USUARIO = ?))";
                            preparedStatement = connection.prepareCall(SQLQuery);
                            preparedStatement.setInt(1, resultSet.getInt("ID"));
                            preparedStatement.setInt(2, Integer.parseInt(userId));
                            preparedStatement.execute();
                        }
                    }
                }
            }
        }
        connection.close();
    }
}
