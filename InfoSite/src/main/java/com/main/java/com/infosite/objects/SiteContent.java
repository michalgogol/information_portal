package com.infosite.objects;

import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.List;

public class SiteContent {

    private int article_id;
    private String domain;
    private String title;
    private String article_href;
    private String article_content;
    private String article_header;
    private String img_Href;
    private List<String> authors;
    private List<String> comments;

    public SiteContent(){}

    public SiteContent(int article_id,String title, String img_Href, String article_href , String article_content) {
        this.title = title;
        this.article_href = article_href;
        this.article_content = article_content;
        this.img_Href = img_Href;
        this.article_id = article_id;
    }

    public SiteContent(int article_id,String title, String img_Href, String article_href,List<String> authors,List<String> comments){

        this.title = title;
        this.img_Href = img_Href;
        this.article_href = article_href;
        this.article_id = article_id;
        this.authors = authors;
        this.comments = comments;
    }
    public SiteContent(int article_id,String title, String img_Href, String article_href)
    {
        this.title = title;
        this.img_Href = img_Href;
        this.article_href = article_href;
        this.article_id = article_id;
    }

    public String getArticle_header() {
        return article_header;
    }

    public void setArticle_header(String article_header) {
        this.article_header = article_header;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public int getArticle_id() {
        return article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_href() {
        return article_href;
    }

    public String getImg_Href() {
        return img_Href;
    }

    public List<String> getComments() {
        return comments;
    }
}
