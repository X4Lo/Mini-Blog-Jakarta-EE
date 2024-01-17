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
                        rs.getBoolean("is_locked")
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
}
