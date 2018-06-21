package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.Post;
import com.br.ifcommunity.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class PostDAO {
    public static void addPost(Post post) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet;
        int matterId = 0;
        int userId = 0;

        String SQLQuery = "SELECT * FROM TB_ALUNO WHERE ID = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, post.getAuthorId());
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            userId = resultSet.getInt("ID_USUARIO");
            System.out.println("ID USUARIO = " + userId);
            System.out.println("ID ALUNO = " + resultSet.getInt("ID"));
        }



        SQLQuery = "SELECT * FROM TB_MATERIA WHERE NOME_MATERIA = ?";
        preparedStatement = connection.prepareStatement(SQLQuery);
        preparedStatement.setString(1, post.getMatterName());
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            matterId = resultSet.getInt("ID");
        }


        SQLQuery = "CALL SP_INSERE_POSTAGEM (?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(SQLQuery);

        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, matterId);
        preparedStatement.setString(3, post.getTitle());
        preparedStatement.setString(4, post.getProgrammingLanguage());
        preparedStatement.setString(5, post.getPostText());

        preparedStatement.executeUpdate();
    }

    public static ArrayList<Post> getPost(String matter, String dateLastPost) throws SQLException {
        ArrayList<Post> listPost = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = ConnectionFactory.getConnection();
        String sql;

        if (matter.equals("")) {
            if (dateLastPost.equals("")) {
                sql = "SELECT * FROM TB_POSTAGEM"
                        + " INNER JOIN TB_USUARIO ON TB_POSTAGEM.ID_USUARIO = TB_USUARIO.ID"
                        + " INNER JOIN TB_ALUNO ON (TB_ALUNO.ID_USUARIO = TB_USUARIO.ID)"
                        + " INNER JOIN TB_MATERIA ON (TB_POSTAGEM.ID_MATERIA = TB_MATERIA.ID)"
                        + " ORDER BY TB_POSTAGEM.DT_REGISTRO DESC";

                preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
            } else {
                sql = "SELECT * FROM TB_POSTAGEM"
                        + " INNER JOIN TB_USUARIO ON TB_POSTAGEM.ID_USUARIO = TB_USUARIO.ID"
                        + " INNER JOIN TB_ALUNO ON (TB_ALUNO.ID_USUARIO = TB_USUARIO.ID)"
                        + " INNER JOIN TB_MATERIA ON (TB_POSTAGEM.ID_MATERIA = TB_MATERIA.ID)"
                        + " WHERE TB_POSTAGEM.DT_REGISTRO > ?"
                        + " ORDER BY TB_POSTAGEM.DT_REGISTRO DESC";

                preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
                preparedStatement.setString(1, dateLastPost);
            }
        } else {
            if (dateLastPost.equals("")) {
                sql = "SELECT * FROM TB_POSTAGEM"
                        + " INNER JOIN TB_USUARIO ON TB_POSTAGEM.ID_USUARIO = TB_USUARIO.ID"
                        + " INNER JOIN TB_ALUNO ON (TB_ALUNO.ID_USUARIO = TB_USUARIO.ID)"
                        + " INNER JOIN TB_MATERIA ON (TB_POSTAGEM.ID_MATERIA = TB_MATERIA.ID)"
                        + " WHERE NOME_MATERIA = ?"
                        + " ORDER BY TB_POSTAGEM.DT_REGISTRO DESC";

                preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
                preparedStatement.setString(1, matter);
            } else {
                sql = "SELECT * FROM TB_POSTAGEM"
                        + " INNER JOIN TB_USUARIO ON TB_POSTAGEM.ID_USUARIO = TB_USUARIO.ID"
                        + " INNER JOIN TB_ALUNO ON (TB_ALUNO.ID_USUARIO = TB_USUARIO.ID)"
                        + " INNER JOIN TB_MATERIA ON (TB_POSTAGEM.ID_MATERIA = TB_MATERIA.ID)"
                        + " WHERE NOME_MATERIA = ? AND TB_POSTAGEM.DT_REGISTRO > ?"
                        + " ORDER BY TB_POSTAGEM.DT_REGISTRO DESC";

                preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
                preparedStatement.setString(1, matter);
                preparedStatement.setString(2, dateLastPost);
            }
        }

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Post post = new Post(resultSet.getInt("TB_POSTAGEM.ID"),
                    resultSet.getString("NOME_MATERIA"),
                    resultSet.getString("NOME"),
                    resultSet.getString("TITULO"),
                    resultSet.getString("POSTAGEM"),
                    resultSet.getString("LINGUAGEM_POSTAGEM"),
                    resultSet.getString("TB_POSTAGEM.DT_REGISTRO"));

            listPost.add(post);
        }

        connection.close();

        return listPost;
    }
}

