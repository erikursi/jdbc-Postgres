package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
    private static final String URL = "jdbc:postgresql://localhost:5433/my_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    public User getUserByLogin(String login) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "SELECT u.login, u.password, u_e.email, u.date " +
                    "FROM users u JOIN user_emails u_e " +
                    "ON u.login = u_e.login " +
                    "WHERE u.login = '" + login + "'";
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ignored) {
            }

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ignored) {

            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ignored) {

            }
        }
        return null;
    }

    public int addUser(User user) {
        int rowsInserted = 0;
        String query = "INSERT INTO users (login, password, date) VALUES (?, ?, ?);\nINSERT INTO user_emails (login, email) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, user.getDate());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getEmail());
            rowsInserted += preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }
}
