package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.User;
import com.br.ifcommunity.regex.PasswordValidation;
import com.br.ifcommunity.regex.VerificationRegister;
import com.br.ifcommunity.util.ConnectionFactory;

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
                    resultSet.getString("MATRICULA"),
                    null,
                    resultSet.getString("DATA_REGISTRO"),
                    resultSet.getString("DATA_ATUALIZACAO")
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

        String SQLQuery = "SELECT * FROM VW_RECUPERA_ALUNO WHERE MATRICULA = ? OR EMAIL = ? OR USUARIO = ?";


        preparedStatement = connection.prepareStatement(SQLQuery);


        System.out.println("User request body: " + userRequestBody);

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
                            resultSet.getString("MATRICULA"),
                            resultSet.getString("HASH_FOTO"),
                            resultSet.getString("DATA_REGISTRO"),
                            resultSet.getString("DATA_ATUALIZACAO")
                    );
                }
//                else if (resultSet.getInt("TIPO_DE_REGISTRO") == 2) { // Case the user is Teacher
//                    user = new User(
//                            resultSet.getInt("ID_USUARIO"),
//                            resultSet.getInt("ID_PROFESSOR"),
//                            resultSet.getString("USUARIO"),
//                            resultSet.getString("NOME"),
//                            resultSet.getString("TELEFONE"),
//                            resultSet.getString("EMAIL"),
//                            resultSet.getInt("TIPO_DE_REGISTRO"),
//                            resultSet.getInt("PERIODO"),
//                            resultSet.getString("MATRICULA")
//                    );
//                } else { // Case the user is Administrator
//
//                }
            }
        }

        connection.close();
        return user;

    }

    public static User updateStudent(User studentRequestBody) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionFactory.getConnection();
        User student = null;

        System.out.println("User student request body: " + studentRequestBody);

        String SQLQuery = "CALL SP_ATUALIZA_PERFIL(?, ?, ?, ?)";

        preparedStatement = connection.prepareCall(SQLQuery);

        preparedStatement.setInt(1, studentRequestBody.getUserId());
        preparedStatement.setString(2, studentRequestBody.getMail());
        preparedStatement.setString(3, studentRequestBody.getName());
        preparedStatement.setString(4, studentRequestBody.getPhone());

        resultSet = preparedStatement.executeQuery();

        SQLQuery = "SELECT * FROM VW_RECUPERA_ALUNO WHERE EMAIL = ?";
        preparedStatement = connection.prepareStatement(SQLQuery);

        preparedStatement.setString(1, studentRequestBody.getMail());
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
                    resultSet.getString("MATRICULA"),
                    resultSet.getString("HASH_FOTO"),
                    resultSet.getString("DATA_REGISTRO"),
                    resultSet.getString("DATA_ATUALIZACAO")
            );
            System.out.println("User dentro do recupera aluno passando novo email: " + student);
        }

        connection.close();
        return student;
    }

    public static String verifyIsNotRegister(String verifyString) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQLQuery = null;
        Connection connection = ConnectionFactory.getConnection();

        if (verifyString.contains("@")) {
            SQLQuery = "SELECT * FROM TB_USUARIO WHERE EMAIL = ?";

            preparedStatement = connection.prepareCall(SQLQuery);
            preparedStatement.setString(1, verifyString);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return "Email já cadastrado!";
            }
        } else if (VerificationRegister.enrolledNumberValidate(verifyString)) {
            SQLQuery = "SELECT * FROM TB_ALUNO WHERE MATRICULA = ?";

            preparedStatement = connection.prepareCall(SQLQuery);
            preparedStatement.setString(1, verifyString);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return "Matricula já cadastrada!";
            }
        } else {
            SQLQuery = "SELECT * FROM TB_USUARIO WHERE USUARIO = ?";

            preparedStatement = connection.prepareCall(SQLQuery);
            preparedStatement.setString(1, verifyString);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return "Usuario já cadastrado!";
            }
        }

        return "Pode cadastrar!";
    }

    public static int uploadPhoto(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet = 0;
        Connection connection = ConnectionFactory.getConnection();

        String SQLQuery = "UPDATE TB_USUARIO SET HASH_FOTO = ? WHERE ID = ?";

        preparedStatement = connection.prepareCall(SQLQuery);

        preparedStatement.setString(1, user.getPhotoHash());
        preparedStatement.setInt(2, user.getUserId());

        resultSet = preparedStatement.executeUpdate();

        connection.close();
        return resultSet;
    }
}


//class main {
//    public static void main(String[] args) {
//        try {
//            System.out.println(UserDAO.verifyIsNotRegister("01858618657-"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
