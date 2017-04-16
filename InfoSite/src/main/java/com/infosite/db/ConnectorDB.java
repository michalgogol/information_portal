package com.infosite.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectorDB
{
    private static Connection conn;
    private static PreparedStatement query;
    private static Statement state;
    final static String DB_url ="jdbc:mysql://localhost:3306/";
    final static String DBUser ="root";
    final static String DBPass ="";

    public static Connection setConnect()
    {
        try{

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DB_url,DBUser,DBPass);
            state = conn.createStatement();
            state.execute("SET NAMES 'UTF8'");
            state.execute("USE infoportal");

        }catch(Exception ex){ex.printStackTrace();}
        finally {
            return conn;
        }
    }

}
