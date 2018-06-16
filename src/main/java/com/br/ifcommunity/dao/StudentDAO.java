package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.Student;
import com.br.ifcommunity.model.User;
import com.br.ifcommunity.regex.Regex;
import com.br.ifcommunity.util.ConnectionFactory;
import com.br.ifcommunity.util.EncryptPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public static String verifyRegister(User user) throws SQLException {
        Student student = (Student) user;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        String errors = "";

        // Verifica se o login já não está cadastrado.
        String sql = "SELECT * FROM TB_USUARIO WHERE USUARIO = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, student.getUser());
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            errors = "Login já cadastrado! ";
        }

        // Verifica se o email já não está cadastrado.
        sql = "SELECT * FROM TB_USUARIO WHERE EMAIL = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, student.getMail());
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            errors += "Email já cadastrado! ";
        }

        // Verifica se o email já não está cadastrado.
        sql = "SELECT * FROM TB_ALUNO WHERE MATRICULA = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, student.getEnrolledNumber());
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            errors += "Matricula já cadastrada!";
        }

        // Verifica errors de regex na senha.
        Regex regex = new Regex();
        regex.setPassword(student.getPassword());
        errors += " " + regex.getErrors();

        return errors;
    }

    public static Student register(User user) throws SQLException {
        Student student = (Student) user;
        String encryptedPassword = new EncryptPassword(student.getPassword()).encrypt();
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();

        String sql = "CALL CADASTRO_USUARIO(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, student.getUser());
        preparedStatement.setString(2, encryptedPassword);
        preparedStatement.setString(3, student.getMail());
        preparedStatement.setString(4, student.getName());
        preparedStatement.setInt(5, student.getPeriod());
        preparedStatement.setString(6, student.getEnrolledNumber());
        preparedStatement.setString(7, student.getPhone());
        preparedStatement.setInt(8, 1);
        preparedStatement.setString(9, null);

        resultSet = preparedStatement.executeQuery();
        connection.close();

        return student;
    }

}
