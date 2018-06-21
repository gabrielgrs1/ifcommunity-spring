package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.Post;
import com.br.ifcommunity.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDAO {
    public static void addPost(Post post) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet;
        int matterID = 0;

        String sql = "CALL SP_INSERE_POSTAGEM (?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, post.getAuthorId());
        preparedStatement.setString(2, post.getMatterName());
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
                sql = "SELECT * FROM VW_RECUPERA_POSTAGEM";

                preparedStatement = connection.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM TB_POSTAGEM"
                        + " INNER JOIN TB_ALUNO ON (TB_ALUNO.ID = TB_POSTAGEM.ID_ALUNO)"
                        + " INNER JOIN TB_MATERIA ON (TB_POSTAGEM.ID_MATERIA = TB_MATERIA.ID)"
                        + " WHERE TB_POSTAGEM.DT_REGISTRO > ?"
                        + " ORDER BY TB_POSTAGEM.DT_REGISTRO DESC";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, dateLastPost);
            }
        } else {
            if (dateLastPost.equals("")) {
                sql = "SELECT * FROM TB_POSTAGEM"
                        + " INNER JOIN TB_ALUNO ON (TB_ALUNO.ID = TB_POSTAGEM.ID_ALUNO)"
                        + " INNER JOIN TB_MATERIA ON (TB_POSTAGEM.ID_MATERIA = TB_MATERIA.ID)"
                        + " WHERE NOME_MATERIA = ?"
                        + " ORDER BY TB_POSTAGEM.DT_REGISTRO DESC";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, matter);
            } else {
                sql = "SELECT * FROM TB_POSTAGEM"
                        + " INNER JOIN TB_ALUNO ON (TB_ALUNO.ID = TB_POSTAGEM.ID_ALUNO)"
                        + " INNER JOIN TB_MATERIA ON (TB_POSTAGEM.ID_MATERIA = TB_MATERIA.ID)"
                        + " WHERE NOME_MATERIA = ? AND TB_POSTAGEM.DT_REGISTRO > ?"
                        + " ORDER BY TB_POSTAGEM.DT_REGISTRO DESC";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, matter);
                preparedStatement.setString(2, dateLastPost);
            }
        }

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Post post = new Post(resultSet.getInt("ID_POSTAGEM"),
                    resultSet.getString("MATERIA"),
                    resultSet.getString("AUTOR"),
                    resultSet.getString("TITULO"),
                    resultSet.getString("POSTAGEM"),
                    resultSet.getString("LINGUAGEM_POSTAGEM"),
                    resultSet.getString("DATA_REGISTRO"));

            listPost.add(post);
        }

        connection.close();

        return listPost;
    }
}

