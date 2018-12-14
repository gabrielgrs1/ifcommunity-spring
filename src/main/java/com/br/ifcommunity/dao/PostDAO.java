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
    public static String addPost(Post post) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet;
        int resultSetInt;
        String resultString;
        String token = post.getAuthorId().split(";")[0];

        post.setAuthorId(post.getAuthorId().split(";")[1]);

        String SQLQuery = "SELECT * FROM TB_USUARIO WHERE ID = ? AND TOKEN = ?";
        preparedStatement = connection.prepareStatement(SQLQuery);
        preparedStatement.setInt(1, Integer.parseInt(post.getAuthorId()));
        preparedStatement.setString(2, token);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            SQLQuery = "CALL SP_INSERE_POSTAGEM (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(SQLQuery);
            preparedStatement.setInt(1, Integer.parseInt(post.getAuthorId()));
            preparedStatement.setString(2, post.getMatterName());
            preparedStatement.setString(3, post.getTitle());
            preparedStatement.setString(4, post.getProgrammingLanguage());
            preparedStatement.setString(5, post.getPostText());

            resultSetInt = preparedStatement.executeUpdate();

            if (resultSetInt > 0) {
                resultString = "Postagem inserida com sucesso!";
            } else {
                resultString = "Erro ao inserir postagem!";
            }

        } else {
            resultString = "Usuário inválido!";
        }

        connection.close();
        return resultString;
    }

    public static ArrayList<Post> getPost(String matter, String dateLastPost) throws SQLException {
        ArrayList<Post> listPost = new ArrayList<>();
        ArrayList<LikeDeslikePost> likeDeslikePostArrayList = getQtdLike();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = ConnectionFactory.getConnection();
        String SQLQuery;

        if (matter.equals("")) {
            if (dateLastPost.equals("")) {
                SQLQuery = "SELECT * FROM VW_RECUPERA_POSTAGEM"
                        + " ORDER BY DATA_REGISTRO DESC";

                preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
            } else {
                SQLQuery = "SELECT * FROM VW_RECUPERA_POSTAGEM"
                        + " WHERE DATA_REGISTRO > ?"
                        + " ORDER BY DATA_REGISTRO DESC";

                preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                preparedStatement.setString(1, dateLastPost);
            }
        } else {
            if (dateLastPost.equals("")) {
                SQLQuery = "SELECT * FROM VW_RECUPERA_POSTAGEM"
                        + " WHERE MATERIA = ?"
                        + " ORDER BY DATA_REGISTRO DESC";

                preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                preparedStatement.setString(1, matter);
            } else {
                SQLQuery = "SELECT * FROM VW_RECUPERA_POSTAGEM"
                        + " WHERE MATERIA = ? AND DATA_REGISTRO > ?"
                        + " ORDER BY DATA_REGISTRO DESC";

                preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                preparedStatement.setString(1, matter);
                preparedStatement.setString(2, dateLastPost);
            }
        }

        resultSet = preparedStatement.executeQuery();

        makeArrayListPost(resultSet, listPost, likeDeslikePostArrayList);

        connection.close();
        return listPost;
    }

    public static String likeDeslike(LikeDeslikePost likeStructure) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet;
        int idUserPost = 0;
        String token = likeStructure.getIdAuthor().split(";")[0];
        likeStructure.setIdAuthor(likeStructure.getIdAuthor().split(";")[1]);

        String SQLQuery = "SELECT * FROM TB_USUARIO WHERE ID = ? AND TOKEN = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, Integer.parseInt(likeStructure.getIdAuthor()));
        preparedStatement.setString(2, token);
        resultSet = preparedStatement.executeQuery();


        if (resultSet.next()) {
            SQLQuery = "SELECT * FROM TB_CONTAGEM_LIKE" +
                    " INNER JOIN TB_POSTAGEM TP on TB_CONTAGEM_LIKE.ID_POSTAGEM = TP.ID" +
                    " WHERE ID_POSTAGEM = ? AND TB_CONTAGEM_LIKE.ID_USUARIO = ?";
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
            preparedStatement.setInt(1, likeStructure.getIdPost());
            preparedStatement.setInt(2, Integer.parseInt(likeStructure.getIdAuthor()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idUserPost = resultSet.getInt("TP.ID_USUARIO");
                if (idUserPost != Integer.parseInt(likeStructure.getIdAuthor())) {
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
                if (idUserPost == Integer.parseInt(likeStructure.getIdAuthor())) {
                    connection.close();
                    return "Usuário não pode dar like na própria postagem!";
                }

                SQLQuery = "INSERT INTO TB_CONTAGEM_LIKE (ID_POSTAGEM, ID_USUARIO, LIKE_DESLIKE) VALUES (?, ?, ?)";
                preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                preparedStatement.setInt(1, likeStructure.getIdPost());
                preparedStatement.setInt(2, Integer.parseInt(likeStructure.getIdAuthor()));
                preparedStatement.setInt(3, likeStructure.getIsLike());
                preparedStatement.executeUpdate();


                connection.close();
                return "Like/Deslike realizado com sucesso!";

            }

            connection.close();
            return "Erro desconhecido!";
        }

        connection.close();
        return "Usuário inválido!";
    }

    private static ArrayList<LikeDeslikePost> getQtdLike() throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet;
        ArrayList<LikeDeslikePost> listLike = new ArrayList<>();

        String SQLQuery = "SELECT * FROM TB_CONTAGEM_LIKE";

        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            listLike.add(
                    new LikeDeslikePost(
                            resultSet.getInt("ID_POSTAGEM"),
                            Integer.toString(resultSet.getInt("ID_USUARIO")),
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
        ResultSet resultSet;
        String resultString = "Erro desconhecido!";
        String token = postRequestBody.getUserId().split(";")[0];
        postRequestBody.setUserId(postRequestBody.getUserId().split(";")[1]);

        String SQLQuery = "SELECT * FROM TB_USUARIO WHERE ID = ? AND TOKEN = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, Integer.parseInt(postRequestBody.getUserId()));
        preparedStatement.setString(2, token);
        resultSet = preparedStatement.executeQuery();


        if (resultSet.next()) {
            SQLQuery = "SELECT * FROM TB_POSTAGEM WHERE ID = ?";
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
            preparedStatement.setInt(1, postRequestBody.getPostId());
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                if (Integer.parseInt(postRequestBody.getUserId()) == resultSet.getInt("ID_USUARIO")) {
                    SQLQuery = "UPDATE TB_POSTAGEM " +
                            "SET TITULO = ?, POSTAGEM = ?, LINGUAGEM_POSTAGEM = ?, DT_ATUALIZACAO = CURRENT_TIMESTAMP " +
                            "WHERE ID = ?";
                    preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                    preparedStatement.setString(1, postRequestBody.getTitle());
                    preparedStatement.setString(2, postRequestBody.getPostText());
                    preparedStatement.setString(3, postRequestBody.getProgrammingLanguage());
                    preparedStatement.setInt(4, postRequestBody.getPostId());
                    preparedStatement.executeUpdate();

                    resultString = "Postagem editada com sucesso!";
                } else {
                    resultString = "Usuário não tem permissão para editar essa postagem!";
                }
            }
        } else {
            resultString = "Usuário inválido!";
        }

        connection.close();
        return resultString;
    }

    public static String removePost(PostEdited postRequestBody) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet;
        String resultString = "Erro desconhecido!";
        String token = postRequestBody.getUserId().split(";")[0];
        postRequestBody.setUserId(postRequestBody.getUserId().split(";")[1]);

        String SQLQuery = "SELECT * FROM TB_USUARIO WHERE ID = ? AND TOKEN = ?";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setInt(1, Integer.parseInt(postRequestBody.getUserId()));
        preparedStatement.setString(2, token);
        resultSet = preparedStatement.executeQuery();


        if (resultSet.next()) {
            SQLQuery = "SELECT * FROM TB_POSTAGEM WHERE ID = ?";
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
            preparedStatement.setInt(1, postRequestBody.getPostId());
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                if (Integer.parseInt(postRequestBody.getUserId()) == resultSet.getInt("ID_USUARIO")) {
                    SQLQuery = "DELETE FROM TB_POSTAGEM WHERE ID = ?";
                    preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
                    preparedStatement.setInt(1, postRequestBody.getPostId());
                    preparedStatement.executeUpdate();

                    resultString = "Postagem removida com sucesso!";
                } else {
                    resultString = "Usuário não tem permissão para remover essa postagem!";
                }
            }
        } else {
            resultString = "Usuário inválido!";
        }

        connection.close();
        return resultString;
    }

    public static ArrayList<Post> searchPost(String searchKeyword, String matter) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet;
        ArrayList<Post> listPost = new ArrayList<>();
        ArrayList<LikeDeslikePost> likeDeslikePostArrayList = getQtdLike();
        searchKeyword = "%" + searchKeyword + "%";

        String SQLQuery = "SELECT * FROM VW_RECUPERA_POSTAGEM WHERE" +
                " MATERIA = ? AND (AUTOR LIKE ?" +
                " OR POSTAGEM LIKE ?" +
                " OR TITULO LIKE ?" +
                " OR LINGUAGEM_POSTAGEM LIKE ?)";
        preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQLQuery);
        preparedStatement.setString(1, matter);
        preparedStatement.setString(2, searchKeyword);
        preparedStatement.setString(3, searchKeyword);
        preparedStatement.setString(4, searchKeyword);
        preparedStatement.setString(5, searchKeyword);
        resultSet = preparedStatement.executeQuery();

        makeArrayListPost(resultSet, listPost, likeDeslikePostArrayList);

        return listPost;
    }

    private static void makeArrayListPost(ResultSet resultSet, ArrayList<Post> listPost, ArrayList<LikeDeslikePost> likeDeslikePostArrayList) throws SQLException {
        while (resultSet.next()) {
            Post post = new Post(resultSet.getInt("ID_POSTAGEM"),
                    resultSet.getString("AUTOR"),
                    resultSet.getString("MATERIA"),
                    resultSet.getString("TITULO"),
                    resultSet.getString("POSTAGEM"),
                    resultSet.getString("LINGUAGEM_POSTAGEM"),
                    resultSet.getString("DATA_REGISTRO"),
                    resultSet.getString("DATA_ATUALIZACAO"),
                                 null;
                  //  resultSet.getString("FOTO_AUTOR"));

            for (int i = 0; i < likeDeslikePostArrayList.size(); i++) {
                if (likeDeslikePostArrayList.get(i).getIdPost() == resultSet.getInt("ID_POSTAGEM")) {
                    post.getLikeDeslikePosts().add(likeDeslikePostArrayList.get(i));
                }
            }

            listPost.add(post);
        }
    }
}

