package com.mofinloans.app.database;

import java.sql.*;

public class Database {

    private Connection connection;

    public Database() {

    }

    public PreparedStatement getPreparedStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet executePreparedStatement(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
