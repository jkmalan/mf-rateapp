package com.mofinloans.app.database;

import com.mofinloans.app.Configuration;

import java.sql.*;
import java.util.Properties;

/**
 *
 *
 * @author jkmalan (John Malandrakis)
 */
public class Database {

    private Configuration config;

    private Connection connection = null;

    public Database(Configuration config) {
        this.config = config;
        connect();
    }

    /**
     * Opens connection to database
     *
     * @return true if successful, else false
     */
    public boolean connect() {
        if (connection != null) {
            return true;
        }

        String source = "jdbc:mysql://"
                + config.getString("db.host") + ":"
                + config.getString("db.port") + "/"
                + config.getString("db.name");
        Properties properties = new Properties();
        properties.put("user", config.getString("db.user"));
        properties.put("password", config.getString("db.pass"));

        try {
            connection = DriverManager.getConnection(source, properties);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }

        return false;
    }

    /**
     * Closes connection to database
     *
     * @return true if successful, else false
     */
    public boolean disconnect() {
        if (connection == null) {
            return true;
        }

        try {
            if (!connection.isClosed())
                connection.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }

        return false;
    }

    /**
     * Get the connection object
     *
     * @return the database connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Prepare a SQL query with a prepared statement
     *
     * @param query the SQL query
     * @return the prepared statement, else null
     */
    public PreparedStatement prepare(String query) {
        if (connection == null) {
            System.out.println("Null statement");
            return null;
        }

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }

        return ps;
    }

    /**
     * Execute a SQL query string
     *
     * @param query the query string
     * @return the result set, else null
     */
    public ResultSet query(String query) {
        if (query == null) {
            return null;
        }

        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }

        return rs;
    }

    /**
     * Execute a SQL query from a prepared statement
     *
     * @param statement the prepared statement
     * @return the result set, else null
     */
    public ResultSet query(PreparedStatement statement) {
        if (statement == null) {
            return null;
        }

        ResultSet rs = null;
        try {
            rs = statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }

        return rs;
    }

    /**
     * Execute a SQL update string
     *
     * @param update the update string
     * @return the rows updated, else -1
     */
    public int update(String update) {
        if (update == null) {
            return -1;
        }

        int r = -1;
        try {
            Statement statement = connection.createStatement();
            r = statement.executeUpdate(update);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }

        return r;
    }

    /**
     * Execute a SQL update from a prepared statement
     *
     * @param statement the prepared statement
     * @return the rows updated, else -1
     */
    public int update(PreparedStatement statement) {
        if (statement == null) {
            return -1;
        }

        int r = -1;
        try {
            r = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }

        return r;
    }

    /**
     * Closes the prepared statement
     *
     * @param statement the PreparedStatement to close
     */
    public void closeStatement(PreparedStatement statement) {
        if (statement == null) {
            return;
        }

        try {
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
    }

    /**
     * Closes the result set
     *
     * @param result the ResultSet to close
     */
    public void closeResultSet(ResultSet result) {
        if (result == null) {
            return;
        }

        try {
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
    }

}
