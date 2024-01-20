package com.blog.dao;

import com.blog.beans.User;
import com.blog.tools.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("picture"),
                        rs.getInt("attempts"),
                        rs.getBoolean("locked")
                );

                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("picture"),
                        rs.getInt("attempts"),
                        rs.getBoolean("locked")
                );

                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<User>();

        String query = "SELECT * FROM users;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("picture"),
                        rs.getInt("attempts"),
                        rs.getBoolean("locked")
                );

                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public static boolean Add(User user) {
        String query = "INSERT INTO users VALUES (NULL, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPicture());
            preparedStatement.setInt(4, user.getAttempts());
            preparedStatement.setBoolean(5, user.isLocked());

            int updated = preparedStatement.executeUpdate();
            return updated != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean Update(User user) {
        String query = "UPDATE users SET username=?, password=?, picture=?, attempts=?, locked=? WHERE id=?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPicture());
            preparedStatement.setInt(4, user.getAttempts());
            preparedStatement.setBoolean(5, user.isLocked());
            preparedStatement.setInt(6, user.getId());

            int updated = preparedStatement.executeUpdate();

            return updated != 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
