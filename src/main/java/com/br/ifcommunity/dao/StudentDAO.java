package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.User;
import com.br.ifcommunity.regex.PasswordValidation;
import com.br.ifcommunity.util.ConnectionFactory;
import com.br.ifcommunity.util.EncryptPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public static String verifyRegister(User user) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        String errors = "";

        // Verifica se o login já não está cadastrado.
        String sql = "SELECT * FROM TB_USUARIO WHERE USUARIO = ?";
        preparedStatement = connection.prepareStatement(sql);
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

        System.out.println("Erros: " + errors);

        return errors;
    }

    public static User register(User user) throws SQLException {
        String encryptedPassword = new EncryptPassword(user.getPassword()).encrypt();
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();

        String SQLQuery = "CALL CADASTRO_USUARIO(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(SQLQuery);

        preparedStatement.setString(1, user.getUser());
        preparedStatement.setString(2, encryptedPassword);
        preparedStatement.setString(3, user.getMail());
        preparedStatement.setString(4, user.getName());
        preparedStatement.setInt(5, user.getPeriod());
        preparedStatement.setString(6, user.getEnrolledNumber());
        preparedStatement.setString(7, user.getPhone());
        preparedStatement.setInt(8, 1);
        preparedStatement.setString(9, null);

        resultSet = preparedStatement.executeQuery();
        connection.close();



        return user;
    }

}
