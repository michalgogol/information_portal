package com.infosite.objects;

public class SiteContent {

    private int article_id;
    private String domain;
    private String title;
    private String article_href;
    private String article_content;
    private String article_header;
    private String img_Href;

    public SiteContent(){}

    public SiteContent(int article_id,String title, String img_Href, String article_href , String article_content) {
        this.title = title;
        this.article_href = article_href;
        this.article_content = article_content;
        this.img_Href = img_Href;
        this.article_id = article_id;
    }

    public SiteContent(int article_id,String title, String img_Href, String article_href){

        this.title = title;
        this.img_Href = img_Href;
        this.article_href = article_href;
        this.article_id = article_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public void setArticle_href(String article_href) {
        this.article_href = article_href;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_header() {
        return article_header;
    }

    public void setArticle_header(String article_header) {
        this.article_header = article_header;
    }

    public String getImg_Href() {
        return img_Href;
    }

    public void setImg_Href(String img_Href) {
        this.img_Href = img_Href;
    }
}
