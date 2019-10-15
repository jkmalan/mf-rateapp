package com.mofinloans.app.database;

import java.sql.*;

public class Database {

    private Connection connection;

    public Database(String host, String port, String name, String user, String pass) {
        String dsn = "jdbc:mysql://" + host + ":" + port + "/" + name;
        try {
            connection = DriverManager.getConnection(dsn, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public ResultSet executePreparedStatement(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public ResultSet execute(String query) {
        ResultSet rs = null;
        try {
            Statement s = connection.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

}
