package com.ruralmc.plugin.Libraries.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {

    protected Connection connection;

    /**
     * Creates new Database
     */
    protected Database() {
        this.connection = null;
    }

    /**
     * Opens a connection with the database
     *
     * @return Opened connection
     * @throws SQLException
     *      if the connection can not be opened
     * @throws ClassNotFoundException
     *      if the driver can not be found
     */
    public abstract Connection openConnection() throws SQLException,
            ClassNotFoundException;

    /**
     * Checks if a connection is open with the database
     *
     * @return true if connection is open
     * @throws SQLException
     *      if the connection cannot be checked
     */
    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    /**
     * Gets the connection with the database
     *
     * @return Connection with the database, null if none
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the connection with the database;
     *
     * @return true if successful, false=connection is null
     * @throws SQLException
     *      if the connection cannot be closed
     */
    public boolean closeConnection() throws SQLException {
        if (connection == null) {
            return false;
        }

        connection.close();
        return true;
    }

    /**
     * Executes an SQL query
     *
     * if the connection is closed it will be open
     *
     * @param query
     *      Query to be run
     * @return the results of the query
     * @throws SQLException
     *      If the query cannot be executed
     * @throws ClassNotFoundException
     *      If the driver cannot be found; see {@link #openConnection()}
     */
    public ResultSet querySQL(String query) throws SQLException, ClassNotFoundException {
        if (!checkConnection()) {
            openConnection();
        }

        Statement statement = connection.createStatement();

        return statement.executeQuery(query);
    }

    /**
     * Executes an Update SQL Query
     * See {@link java.sql.Statement#executeUpdate(String)}
     * If the connection is closed, it will be opened
     *
     * @param query
     *            Query to be run
     * @return Result Code, see {@link java.sql.Statement#executeUpdate(String)}
     * @throws SQLException
     *             If the query cannot be executed
     * @throws ClassNotFoundException
     *             If the driver cannot be found; see {@link #openConnection()}
     */
    public int updateSQL(String query) throws SQLException, ClassNotFoundException {
        if (!checkConnection()) {
            openConnection();
        }

        Statement statement = connection.createStatement();

        return statement.executeUpdate(query);
    }
}
