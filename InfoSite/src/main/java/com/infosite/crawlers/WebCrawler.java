package com.infosite.crawlers;

import java.io.IOException;
import java.net.SocketTimeoutException;
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
    private String[] linkers,contentSelectors,headAndImgSelectors,imgSelectors;
    private List<Selector> selectors;
    private List<Integer> absentArticles;
    private int sizeOfArticlesTable;

    public WebCrawler(String category) {


        selectors = new CategorySelectors().getAllSelectors(category);
        linkers = new String[selectors.size()];

        for(int i = 0; i < selectors.size();i++)
            linkers[i] = selectors.get(i).getDomainAdress();

    }

    public List<SiteContent> getTitleAndHref() throws IOException,IllegalArgumentException {

        Elements titAndHref;
        int iterator = 0;
        contentOfArticle = new ArrayList<SiteContent>();
        sizeOfArticlesTable=0;
        absentArticles = new ArrayList<Integer>();

        for(int i =0; i< linkers.length; i++) {

            document = Jsoup.connect(linkers[i]).get();

            contentSelectors = selectors.get(i).getContentSelector().split(Pattern.quote("&&"));
            imgSelectors = selectors.get(i).getImageHref().split(Pattern.quote("&&"));

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


            for (int j = sizeOfArticlesTable; j < contentOfArticle.size(); j++) {


                try {

                    document = Jsoup.connect(contentOfArticle.get(j).getArticle_href()).timeout(1000).get();

                } catch (SocketTimeoutException ex) {
                    contentOfArticle.get(j).setArticle_content("");
                    break;
                }


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

                for(String selector : imgSelectors)
                {

                    if ((document.select(selector).attr("abs:src")).length() < 1) {
                        continue;
                    } else
                        contentOfArticle.get(j).setImg_Href(document.select(selector).attr("abs:src"));
                }


            }


        }

        for(int i =0; i<contentOfArticle.size();i++)
        {

            if(contentOfArticle.get(i).getArticle_href().equals(""))
            {
                contentOfArticle.remove(i);
                i=i-1;
            }
        }
        return contentOfArticle;
    }


}
