package com.infosite.db;


import com.infosite.domain.Selector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorySelectors {

    private Connection conn ;
    private PreparedStatement query;
    private List<Selector> links;

    public List<Selector> getAllSelectors(String category)
    {
        conn = ConnectorDB.setConnect();
        links = new ArrayList<Selector>();

        try{
            query = conn.prepareStatement("SELECT Adress,Title,Article_Href, Article_Content,Article_Header,Img_Href FROM selectors WHERE Category =?");
            query.setString(1,category);
            ResultSet res = query.executeQuery();
            while(res.next())
            {
                links.add(new Selector(res.getString("Adress"),res.getString("Title"),res.getString("Article_Href")
                        ,res.getString("Article_Content"),res.getString("Article_Header"),res.getString("Img_Href")));
            }

        }catch(SQLException ex){ex.printStackTrace();}
        finally {
            return links;
        }
    }

    public void addNewsSelectors(String category,String domain, String title,String art_href, String art_cont,String art_head,String img_href)
    {
        conn = ConnectorDB.setConnect();
        try{

            query = conn.prepareStatement("INSERT INTO selectors (Category,Adress,Title,Article_Href, Article_Content,Article_Header,Img_Href) VALUES (?,?,?,?,?,?,?)");
            query.setString(1,category);
            query.setString(2,domain);
            query.setString(3,title);
            query.setString(4,art_href);
            query.setString(5,art_cont);
            query.setString(6,art_head);
            query.setString(7,img_href);

            query.executeUpdate();

        }catch(SQLException ex){ex.printStackTrace();}
    }



}
