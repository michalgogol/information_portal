package com.infosite.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPasswordDB {

    private Connection conn;
    private PreparedStatement query;

    public int checkEmail(String email) { //check if email exist in database and return id of member
        conn = ConnectorDB.setConnect();
        int id = 0;
        try {
            query = conn.prepareStatement("SELECT id FROM users WHERE Email = ?");
            query.setString(1, email);
            ResultSet res = query.executeQuery();

            while (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return id;
        }
    }

    public Boolean setNewPassword(String newPassword,String email) {
        conn = ConnectorDB.setConnect();
        int id = checkEmail(email);
        if(id!=0) {
            try {
                query = conn.prepareStatement("UPDATE users SET Password = ? WHERE id = ?");
                query.setString(1, newPassword);
                query.setInt(2, id);
                query.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return true;
        }
        else
            return false;
    }


}