package com.infosite.db;

import com.infosite.objects.SiteContent;

import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArticlesInDB {
    private Connection conn = ConnectorDB.setConnect();
    private PreparedStatement query;

    public void setArticles(List<SiteContent> titleAndHref, List<String> content, String category) {
       // conn = ConnectorDB.setConnect();


        for (int i = 0; i < titleAndHref.size(); i++) {
            try {

                SiteContent site = titleAndHref.get(i);
                byte[] articles = content.get(i).getBytes("UTF-8");

                query = conn.prepareStatement("SET NAMES 'UTF8'");
                query.execute();
                query = conn.prepareStatement("SELECT article_id FROM articles WHERE article_href =?");
                query.setString(1, site.getArticle_href());
                ResultSet resy = query.executeQuery();

                if (!resy.next()) {
                    query = conn.prepareStatement("INSERT INTO articles VALUE(NULL,?,?,?,?,?)");
                    query.setString(1, site.getArticle_href());
                    query.setString(2, site.getImg_Href());
                    query.setBytes(3, site.getTitle().getBytes("UTF-8"));
                    query.setBytes(4, articles);
                    query.setString(5, category);
                    query.execute();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


    public void removerOldArticles(List<SiteContent> siteContents)
    {
     //   conn = ConnectorDB.setConnect();
        List<String> currentArticles = getCurrentArticles(conn);

       for(SiteContent siteContent : siteContents)
       {
           for(String href : currentArticles)
           {
               if(href.equals(siteContent.getArticle_href()))
                   break;
               else
               {
                   try {
                       System.out.println(href);
                       query = conn.prepareStatement("DELETE FROM articles WHERE articles.article_id=?");
                       query.setString(1, href);
                       query.executeUpdate();
                   }catch (SQLException ex){ex.printStackTrace();}

               }
           }
       }
    }


    public List getCurrentArticles(Connection conn)
    {
        List<String> currentArticles = new ArrayList<>();
        try {
            query = conn.prepareStatement("SELECT article_id FROM articles");
            ResultSet resultSet = query.getResultSet();

            while(resultSet.next())
            {
                currentArticles.add( resultSet.getString("article_href"));
            }
        }catch (SQLException ex){ex.printStackTrace();}
        finally {
            return currentArticles;

        }
    }


    public List<SiteContent> getAricles(String category) {
     //   conn = ConnectorDB.setConnect();
        List<SiteContent> titleAndHrefs = new ArrayList<>();
        List<String> authors = new ArrayList<>();
        List<String> comments = new ArrayList<>();
        String title, href, img;
        int id;
        try {
            query = conn.prepareStatement("SELECT article_id,article_href,article_img,article_title FROM articles WHERE Category=?");
            query.setString(1, category);
            ResultSet res = query.executeQuery();
            while (res.next()) {
                title = new String(res.getBytes("article_title"), Charset.forName("UTF-8"));
                href = res.getString("article_href");
                img = res.getString("article_img");
                id = res.getInt("article_id");

                query = conn.prepareStatement("SELECT comment, author FROM comments WHERE article_id=?");
                query.setInt(1,id);
                ResultSet resultSet =  query.executeQuery();

                while (resultSet.next())
                {
                    authors.add(resultSet.getString("author"));
                    comments.add(resultSet.getString("comment"));
                }

                titleAndHrefs.add(new SiteContent(id, title, img, href,authors,comments));
                authors = new ArrayList<>();
                comments = new ArrayList<>();

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return titleAndHrefs;
    }

    public String getContent(String url) {
     //   conn = ConnectorDB.setConnect();

        String content = "";
        try {
            query = conn.prepareStatement("SELECT article_content FROM articles WHERE article_href=?");
            query.setString(1, url);
            ResultSet res = query.executeQuery();
            while (res.next()) {
                content = new String(res.getBytes("article_content"), Charset.forName("UTF-8"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return content;

    }


}
