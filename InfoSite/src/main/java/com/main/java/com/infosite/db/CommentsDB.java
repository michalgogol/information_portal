package com.infosite.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentsDB {
    private Connection conn;
    private PreparedStatement query;

    public void addComment(String comment, String login, int id) {
        conn = ConnectorDB.setConnect();

        try {
            query = conn.prepareStatement("INSERT INTO comments (comments_id, comment, author, article_id) VALUES (NULL,?,?,?)");
            query.setString(1, comment);
            query.setString(2, login);
            query.setInt(3, id);
            query.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}