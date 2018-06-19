package com.br.ifcommunity.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String serverName = "ifcommunity.mysql.uhserver.com";
            String database = "ifcommunity";
            String url = "jdbc:mysql://" + serverName + "/" + database;
            String user = "admin_if";
            String password = "Admintegrador@1";

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getCause());
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