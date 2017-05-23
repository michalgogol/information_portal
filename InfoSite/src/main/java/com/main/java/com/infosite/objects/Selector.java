package com.infosite.objects;


public class Selector {

    private String domainAdress;
    private String titleSelector;
    private String articleHrefSelector;
    private String contentSelector;
    private String headSelector;
    private String imageHref;

    public Selector(){}

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

    public void setDomainAdress(String domainAdress) {
        this.domainAdress = domainAdress;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    public void setArticleHrefSelector(String articleHrefSelector) {
        this.articleHrefSelector = articleHrefSelector;
    }

    public void setContentSelector(String contentSelector) {
        this.contentSelector = contentSelector;
    }

    public void setHeadSelector(String headSelector) {
        this.headSelector = headSelector;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
