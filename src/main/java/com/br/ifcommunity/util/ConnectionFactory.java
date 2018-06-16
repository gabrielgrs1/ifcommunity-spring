package com.br.ifcommunity.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String serverName = "us-cdbr-iron-east-04.cleardb.net";
            String database = "heroku_599d365acd76437";
            String url = "jdbc:mysql://" + serverName + "/" + database;
            String user = "b52de50bac48c2";
            String password = "e61df8bd704c3da";

            Connection connection = DriverManager.getConnection(url, user, password);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[]args){
        if (new ConnectionFactory().getConnection() != null) {
            System.out.println("Conectado com sucesso!");
        } else {
            System.out.println("Erro");
        }
    }
}