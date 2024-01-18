package com.ehei.doa;

import com.ehei.beans.User;
import com.ehei.tools.ConnectionDB;
import com.ehei.tools.Tools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
