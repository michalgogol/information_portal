package com.infosite.domain;


public class Selector {

    private String domainAdress;
    private String titleSelector;
    private String articleHrefSelector;
    private String contentSelector;
    private String headSelector;
    private String imageHref;

    public Selector(String domainAdress,String titleSelector,String articleHrefSelector,String contentSelector,String headSelector,String imageHref){

        this.domainAdress = domainAdress;
        this.titleSelector = titleSelector;
        this.articleHrefSelector = articleHrefSelector;
        this.contentSelector = contentSelector;
        this.headSelector = headSelector;
        this.imageHref = imageHref;
    }

    public String getDomainAdress() {
        return domainAdress;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public String getArticleHrefSelector() {
        return articleHrefSelector;
    }


    public String getContentSelector() {
        return contentSelector;
    }

    public String getHeadSelector() {
        return headSelector;
    }

    public String getImageHref() {
        return imageHref;
    }
}
