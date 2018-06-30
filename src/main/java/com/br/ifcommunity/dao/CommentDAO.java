package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.Comment;
import com.br.ifcommunity.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CommentDAO {
    public static String comment(Comment comment) throws SQLException {
        PreparedStatement preparedStatement;
        int resultSetInt;
        ResultSet resultSet;
        Connection connection = ConnectionFactory.getConnection();

        String token = comment.getUserId().split(";")[0];
        String userId = comment.getUserId().split(";")[1];

        String SQLQuery = "SELECT * FROM TB_USUARIO WHERE ID = ? AND TOKEN = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, Integer.parseInt(userId));
        preparedStatement.setString(2, token);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            SQLQuery = "INSERT INTO TB_COMENTARIO_POSTAGEM (ID_COMENTARISTA, ID_POSTAGEM, COMENTARIO) VALUES (?, ?, ?)";
            preparedStatement = Objects.requireNonNull(connection).prepareCall(SQLQuery);
            preparedStatement.setInt(1, Integer.parseInt(userId));
            preparedStatement.setInt(2, comment.getPostId());
            preparedStatement.setString(3, comment.getCommentText());
            resultSetInt = preparedStatement.executeUpdate();

            if (resultSetInt != 0) {
                connection.close();
                return "Comentário inserido com sucesso!";
            }

        } else {
            connection.close();
            return "Usuário inválido!";
        }
        connection.close();
        return "Erro desconhecido!";
    }

    public static ArrayList<Comment> getComments(int postId) throws SQLException {
        ArrayList<Comment> commentArrayList = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = ConnectionFactory.getConnection();
        String SQLQuery = "SELECT * FROM TB_COMENTARIO_POSTAGEM" +
                " INNER JOIN TB_USUARIO ON (TB_USUARIO.ID = TB_COMENTARIO_POSTAGEM.ID_COMENTARISTA)" +
                " WHERE ID_POSTAGEM = ? AND FL_IS_EXCLUDED <> 1";

        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, postId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            commentArrayList.add(
                    new Comment(
                            resultSet.getInt("ID_POSTAGEM"),
                            Integer.toString(resultSet.getInt("ID_COMENTARISTA")),
                            resultSet.getInt("ID"),
                            resultSet.getString("NOME"),
                            resultSet.getString("COMENTARIO"),
                            resultSet.getString("TB_COMENTARIO_POSTAGEM.DT_REGISTRO"),
                            resultSet.getString("HASH_FOTO")
                    )
            );
        }

        connection.close();
        return commentArrayList;
    }
}
