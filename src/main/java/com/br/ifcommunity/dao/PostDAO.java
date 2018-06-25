package com.br.ifcommunity.dao;

import com.br.ifcommunity.model.LikeDeslikePost;
import com.br.ifcommunity.model.Post;
import com.br.ifcommunity.model.PostEdited;
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

        connection.close();
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

            post.setLikeDeslikePosts(getQtdLike(resultSet.getInt("TB_POSTAGEM.ID")));

            listPost.add(post);
        }

        connection.close();

        return listPost;
    }

    public static String likeDeslike(LikeDeslikePost likeStructure) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet = null;
        int idUsuarioPostagem = 0;

        String SQLQuery = "SELECT * FROM TB_CONTAGEM_LIKE" +
                " INNER JOIN TB_POSTAGEM TP on TB_CONTAGEM_LIKE.ID_POSTAGEM = TP.ID " +
                "WHERE ID_POSTAGEM = ? AND TB_CONTAGEM_LIKE.ID_USUARIO = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, likeStructure.getIdPost());
        preparedStatement.setInt(2, likeStructure.getIdAuthor());
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            idUsuarioPostagem = resultSet.getInt("TP.ID_USUARIO");
            if (idUsuarioPostagem != likeStructure.getIdAuthor()) {
                if (likeStructure.getIsLike() != resultSet.getInt("LIKE_DESLIKE")) {
                    SQLQuery = "UPDATE TB_CONTAGEM_LIKE SET LIKE_DESLIKE = ? WHERE ID = ?";
                    preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                    preparedStatement.setInt(1, likeStructure.getIsLike());
                    preparedStatement.setInt(2, resultSet.getInt("ID"));
                    preparedStatement.executeUpdate();

                    connection.close();
                    return "Atualização do Like/Deslike realizado com sucesso!";
                }

                SQLQuery = "DELETE FROM TB_CONTAGEM_LIKE WHERE ID = ? AND LIKE_DESLIKE = ?";
                preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                preparedStatement.setInt(1, resultSet.getInt("ID"));
                preparedStatement.setInt(2, likeStructure.getIsLike());
                preparedStatement.executeUpdate();


                connection.close();
                return "Remoção do Like/Deslike realizado com sucesso!";
            }
        }

        if (likeStructure.isExclude() == 0) {
            if (idUsuarioPostagem == likeStructure.getIdAuthor()) {
                connection.close();
                return "Usuário não pode dar like na própria postagem!";
            }

            SQLQuery = "INSERT INTO TB_CONTAGEM_LIKE (ID_POSTAGEM, ID_USUARIO, LIKE_DESLIKE) VALUES (?, ?, ?)";
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
            preparedStatement.setInt(1, likeStructure.getIdPost());
            preparedStatement.setInt(2, likeStructure.getIdAuthor());
            preparedStatement.setInt(3, likeStructure.getIsLike());
            preparedStatement.executeUpdate();


            connection.close();
            return "Like/Deslike realizado com sucesso!";

        }

        connection.close();
        return "Erro desconhecido!";
    }

    private static ArrayList<LikeDeslikePost> getQtdLike(int postId) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet = null;
        ArrayList<LikeDeslikePost> listLike = new ArrayList<>();
        int like = 0;
        int deslike = 0;

        String SQLQuery = "SELECT * FROM TB_CONTAGEM_LIKE WHERE ID_POSTAGEM = ? ";

        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, postId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            listLike.add(
                    new LikeDeslikePost(
                            resultSet.getInt("ID_USUARIO"),
                            resultSet.getInt("LIKE_DESLIKE")
                    )
            );
        }

        connection.close();
        return listLike;
    }

    public static String editPost(PostEdited postRequestBody) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet = null;

        String SQLQuery = "SELECT * FROM TB_POSTAGEM WHERE ID = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, postRequestBody.getPostId());
        resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {

            if (postRequestBody.getUserId() == resultSet.getInt("ID_USUARIO")) {
                SQLQuery = "UPDATE TB_POSTAGEM " +
                        "SET TITULO = ?, POSTAGEM = ?, LINGUAGEM_POSTAGEM = ? " +
                        "WHERE ID = ?";
                preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                preparedStatement.setString(1, postRequestBody.getTitle());
                preparedStatement.setString(2, postRequestBody.getPostText());
                preparedStatement.setString(3, postRequestBody.getProgrammingLanguage());
                preparedStatement.setInt(4, postRequestBody.getPostId());
                preparedStatement.executeUpdate();
                connection.close();

                return "Postagem editada com sucesso!";
            } else {

                connection.close();
                return "Usuário não tem permissão para editar essa postagem!";
            }
        }

        connection.close();
        return "Erro deseconhecido!";
    }

    public static String removePost(PostEdited postRequestBody) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet = null;

        String SQLQuery = "SELECT * FROM TB_POSTAGEM WHERE ID = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, postRequestBody.getPostId());
        resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            if (postRequestBody.getUserId() == resultSet.getInt("ID_USUARIO")) {
                SQLQuery = "DELETE FROM TB_POSTAGEM WHERE ID = ?";
                preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                preparedStatement.setInt(1, postRequestBody.getPostId());
                preparedStatement.executeUpdate();

                return "Postagem removida com sucesso!";
            } else {
                return "Usuário não tem permissão para remover essa postagem!";
            }
        }

        connection.close();
        return "Erro deseconhecido!";
    }
}

