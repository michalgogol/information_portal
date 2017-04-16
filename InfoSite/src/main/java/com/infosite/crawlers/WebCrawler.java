package com.infosite.crawlers;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import com.infosite.db.CategorySelectors;
import com.infosite.domain.Selector;
import com.infosite.domain.SiteContent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class WebCrawler {

    private Document document;
    private List<SiteContent> contentOfArticle;
    private String[] linkers,contentSelectors,headAndImgSelectors;
    private List<Selector> selectors;
    private List<Integer> absentArticles;
    private int sizeOfArticlesTable;

    public WebCrawler(String category) {


        selectors = new CategorySelectors().getAllSelectors(category);
        linkers = new String[selectors.size()];

        for(int i = 0; i < selectors.size();i++)
            linkers[i] = selectors.get(i).getDomainAdress();

    }

    public List<SiteContent> getTitleAndHref() throws IOException {

        Elements titAndHref;
        int iterator = 0;
        contentOfArticle = new ArrayList<SiteContent>();
        sizeOfArticlesTable=0;
        absentArticles = new ArrayList<Integer>();

        for(int i =0; i< linkers.length; i++) {

            document = Jsoup.connect(linkers[i]).get();

            contentSelectors = selectors.get(i).getContentSelector().split(Pattern.quote("&&"));

            headAndImgSelectors = new String[2];
            headAndImgSelectors[0] = selectors.get(i).getHeadSelector();
            headAndImgSelectors[1] = selectors.get(i).getImageHref();

            titAndHref = document.select( selectors.get(i).getArticleHrefSelector());


            for (int j = 0; j < (titAndHref.size()) / 2; j++) {

                if ( titAndHref.get(j).attr("abs:href").equals("")) {

                    continue;
                }
                else {
                    contentOfArticle.add(new SiteContent());
                    contentOfArticle.get(iterator).setArticle_href(titAndHref.get(j).attr("abs:href"));
                    iterator++;
                }

            }


            for (int j = sizeOfArticlesTable; j < contentOfArticle.size()-1; j++) {

                try {

                    document = Jsoup.connect(contentOfArticle.get(j).getArticle_href()).get();

                } catch (IllegalArgumentException ex) {
                    break;
                }

                contentOfArticle.get(j).setArticle_id(j+1);

                String[] separatedTitle = (document.select(selectors.get(i).getTitleSelector()).text().split(Pattern.quote("-")));

                if(separatedTitle[0].equals(""))
                {
                    absentArticles.add(j);
                    continue;
                }
                else
                    contentOfArticle.get(j).setTitle(separatedTitle[0]);


                for(String selector : contentSelectors)
                {
                    if (document.select(selector).text().length() > 0)
                        contentOfArticle.get(j).setArticle_content(document.select(selector).text());
                    else
                        continue;
                }


                contentOfArticle.get(j).setArticle_header(document.select(selectors.get(i).getHeadSelector()).text());



                if ((document.select(selectors.get(i).getImageHref()).attr("src")).length()<1)
                {
                    System.out.println("zero href: "+separatedTitle[0] + (document.select(selectors.get(i).getImageHref()).attr("src")));
                    absentArticles.add(j);
                    continue;
                }
                else
                    contentOfArticle.get(j).setImg_Href(document.select(selectors.get(i).getImageHref()).attr("src"));


            }
            int baseSize =  contentOfArticle.size();
            int currentSize;
            System.out.println("coL"+absentArticles.size());
/*
            for(int index : absentArticles)
            {
                currentSize = contentOfArticle.size();
                contentOfArticle.remove(index-(baseSize-currentSize));

            }*/



        }

        return contentOfArticle;
    }


}
