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
    public static int comment(Comment comment) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet = 0;
        Connection connection = ConnectionFactory.getConnection();
        String SQLQuery = "";


        SQLQuery = "INSERT INTO TB_COMENTARIO_POSTAGEM (ID_COMENTARISTA, ID_POSTAGEM, COMENTARIO) VALUES (?, ?, ?)";
        preparedStatement = Objects.requireNonNull(connection).prepareCall(SQLQuery);
        preparedStatement.setInt(1, comment.getAuthorId());
        preparedStatement.setInt(2, comment.getPostId());
        preparedStatement.setString(3, comment.getCommentText());
        resultSet = preparedStatement.executeUpdate();

        connection.close();
        return resultSet;
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
                            resultSet.getInt("ID"),
                            resultSet.getString("NOME"),
                            resultSet.getString("COMENTARIO"),
                            resultSet.getString("TB_COMENTARIO_POSTAGEM.DT_REGISTRO")
                    )
            );
        }


        return commentArrayList;
    }
}
