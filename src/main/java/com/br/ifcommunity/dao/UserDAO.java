package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.User;
import com.br.ifcommunity.regex.PasswordValidation;
import com.br.ifcommunity.util.ConnectionFactory;
import com.br.ifcommunity.util.DecryptPassword;
import com.br.ifcommunity.util.EncryptPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserDAO {

    public static String verifyRegister(User user) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = ConnectionFactory.getConnection();
        String errors = "";

        // Verifica se o login já não está cadastrado.
        String sql = "SELECT * FROM TB_USUARIO WHERE USUARIO = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);

        preparedStatement.setString(1, user.getUser());
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            errors = "Login já cadastrado! ";
        }

        // Verifica se o email já não está cadastrado.
        sql = "SELECT * FROM TB_USUARIO WHERE EMAIL = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getMail());
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            errors += "Email já cadastrado! ";
        }

        // Verifica se o email já não está cadastrado.
        sql = "SELECT * FROM TB_ALUNO WHERE MATRICULA = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getEnrolledNumber());
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            errors += "Matricula já cadastrada!";
        }

        // Verifica errors de passwordValidation na senha.
        PasswordValidation passwordValidation = new PasswordValidation(user.getPassword());
        errors += passwordValidation.getErrors();

        connection.close();

        return errors;
    }

    public static User register(User user) throws SQLException {
//        String encryptedPassword = new EncryptPassword(user.getPassword()).encrypt();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        User student = null;

        String SQLQuery = "CALL CADASTRO_USUARIO(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (connection != null) {
            preparedStatement = connection.prepareCall(SQLQuery);
            preparedStatement.setString(1, user.getUser());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getMail());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setInt(5, user.getPeriod());
            preparedStatement.setString(6, user.getEnrolledNumber());
            preparedStatement.setString(7, user.getPhone());
            preparedStatement.setInt(8, 1);
            preparedStatement.setString(9, null);

            resultSet = preparedStatement.executeQuery();
        }

        SQLQuery = "SELECT * FROM VW_RECUPERA_ALUNO WHERE EMAIL = ?";
        preparedStatement = connection.prepareStatement(SQLQuery);

        preparedStatement.setString(1, user.getMail());
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            student = new User(
                    resultSet.getInt("ID_USUARIO"),
                    resultSet.getInt("ID_ALUNO"),
                    resultSet.getString("USUARIO"),
                    resultSet.getString("NOME"),
                    resultSet.getString("TELEFONE"),
                    resultSet.getString("EMAIL"),
                    resultSet.getInt("TIPO_DE_REGISTRO"),
                    resultSet.getInt("PERIODO"),
                    resultSet.getString("MATRICULA")
            );
        }

        connection.close();

        return student;
    }

    public static User login(User userRequestBody) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        User user = null;

        if (!connection.isClosed() || connection != null) {
            String SQLQuery = "SELECT * FROM VW_RECUPERA_ALUNO WHERE MATRICULA = ? OR EMAIL = ? OR USUARIO = ?";
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);

            preparedStatement.setString(1, userRequestBody.getUser());
            preparedStatement.setString(2, userRequestBody.getUser());
            preparedStatement.setString(3, userRequestBody.getUser());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("SENHA").equals(userRequestBody.getPassword())) {
                    if (resultSet.getInt("TIPO_DE_REGISTRO") == 1) {    // Case the user is Student
                        user = new User(
                                resultSet.getInt("ID_USUARIO"),
                                resultSet.getInt("ID_ALUNO"),
                                resultSet.getString("USUARIO"),
                                resultSet.getString("NOME"),
                                resultSet.getString("TELEFONE"),
                                resultSet.getString("EMAIL"),
                                resultSet.getInt("TIPO_DE_REGISTRO"),
                                resultSet.getInt("PERIODO"),
                                resultSet.getString("MATRICULA")
                        );
                    } else if (resultSet.getInt("TIPO_DE_REGISTRO") == 2) { // Case the user is Teacher
                        user = new User(
                                resultSet.getInt("ID_USUARIO"),
                                resultSet.getInt("ID_PROFESSOR"),
                                resultSet.getString("USUARIO"),
                                resultSet.getString("NOME"),
                                resultSet.getString("TELEFONE"),
                                resultSet.getString("EMAIL"),
                                resultSet.getInt("TIPO_DE_REGISTRO"),
                                resultSet.getInt("PERIODO"),
                                resultSet.getString("MATRICULA")
                        );
                    } else { // Case the user is Administrator

                    }
                }
            }
            connection.close();

            return user;
        }

        return new User("Conexão não estabelecida!");
    }


}

//class main {
//    public static void main(String[] args) {
//        User user = null;
//
//        try {
//            user = UserDAO.login("gabriel_guilherme2006@hotmail.com", "Mineiroi@1");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(user);
//    }
//}
