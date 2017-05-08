package com.infosite.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewAccountDB {

    private Connection conn;
    private PreparedStatement query;

    public void setAccount(String log, String pass, String email, String[] Resp)
    {
        conn = ConnectorDB.setConnect();
        try{
            query = conn.prepareStatement("INSERT INTO users VALUE(NULL,?,?,?,?,?,?,?,?)");
            query.setString(1, log);
            query.setString(2, pass);
            query.setString(3,email);
            for(int j = 0;j<Resp.length;j++)
            {
                if(Resp[j]==null)
                    query.setInt(j+4, 0);
                else
                    query.setInt(j+4, 1);

            }
            query.execute();

        }catch(Exception ex){ex.printStackTrace();}


    }

    public String checkLoginIfExist(String log,String mail) // check if login and email not exist yet
    {
        ResultSet result;
        String temp = "";
        conn = ConnectorDB.setConnect();
        try{
            result = conn.createStatement().executeQuery("SELECT id FROM users WHERE Login ='"+log+"'");

            while(result.next())
            {
                temp = result.getString(1);
            }

            result = conn.createStatement().executeQuery("SELECT  id FROM users WHERE Email='"+mail+"'");
            while(result.next())
                temp = temp+result.getString(1);

        }catch(SQLException ex){ex.printStackTrace();}
        finally
        {
            return  temp;
        }
    }
}
