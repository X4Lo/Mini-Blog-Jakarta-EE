package com.ehei.doa;

import com.ehei.beans.Comment;
import com.ehei.tools.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {


    public static List<Comment> getPostCommentsOffset(int postId, int limit, int offset) {
        List<Comment> comments = new ArrayList<Comment>();

        String query = "SELECT * FROM comment WHERE postId=? ORDER BY 'timestamp' DESC LIMIT ? OFFSET ?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {


                Comment comment = new Comment(
                        rs.getInt("id"),
                        rs.getInt("postId"),
                        rs.getInt("authorId"),
                        rs.getString("content"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );

                comments.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }

    private static boolean removeCommentById(int id) {
        String query = "DELETE FROM comments WHERE id=?;";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int updated = preparedStatement.executeUpdate();
            return updated != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean Add(Comment comment) {
        String query = "INSERT INTO comments VALUES (NULL, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, comment.getPostId());
            preparedStatement.setInt(2, comment.getAuthorId());
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(comment.getTimestamp()));

            int updated = preparedStatement.executeUpdate();
            return updated != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
