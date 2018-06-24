package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.Comment;
import com.br.ifcommunity.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class CommentDAO {
    public static int comment(Comment comment) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet = 0;
        Connection connection = ConnectionFactory.getConnection();
        String SQLQuery = "";


        SQLQuery = "INSERT INTO TB_COMENTARIO_POSTAGEM (ID_COMENTARISTA, ID_POSTAGEM, COMENTAIRO) VALUES (?, ?, ?)";
        preparedStatement = Objects.requireNonNull(connection).prepareCall(SQLQuery);
        preparedStatement.setInt(1, comment.getAuthorId());
        preparedStatement.setInt(2, comment.getPostId());
        preparedStatement.setString(3, comment.getCommentText());
        resultSet = preparedStatement.executeUpdate();

        connection.close();
        return resultSet;
    }
}
