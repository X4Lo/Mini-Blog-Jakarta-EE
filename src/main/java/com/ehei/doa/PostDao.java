package com.ehei.doa;

import com.ehei.beans.Post;
import com.ehei.beans.User;
import com.ehei.tools.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    public static Post getPostById(int id) {
        String query = "SELECT * FROM posts WHERE id = ?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("banner"),
                        rs.getInt("authorId"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );

                return post;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Post> getPostsOffset(int limit, int offset) {
        List<Post> posts = new ArrayList<Post>();

        String query = "SELECT * FROM posts ORDER BY 'timestamp' DESC LIMIT ? OFFSET ?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("banner"),
                        rs.getInt("authorId"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );

                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return posts;
    }

    public static List<Post> getPostByAuthorId(int id) {
        List<Post> posts = new ArrayList<Post>();

        String query = "SELECT * FROM posts WHERE authorId=?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("banner"),
                        rs.getInt("authorId"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );

                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return posts;
    }

    public static boolean deletePostById(int id) {
        String query = "DELETE FROM posts WHERE id=?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int updated = preparedStatement.executeUpdate();
            return updated != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean Add(Post post) {
        String query = "INSERT INTO posts VALUES (NULL, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getBanner());
            preparedStatement.setInt(4, post.getAuthorId());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(post.getTimestamp()));

            int updated = preparedStatement.executeUpdate();
            return updated != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean Update(Post post) {
        String query = "UPDATE posts SET title=?, content=?, banner=? WHERE id=?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getBanner());
            preparedStatement.setInt(4, post.getId());

            int updated = preparedStatement.executeUpdate();
            return updated != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
