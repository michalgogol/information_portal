package com.infosite.crawlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import com.infosite.db.CategorySelectors;
import com.infosite.objects.Selector;
import com.infosite.objects.SiteContent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebCrawler {

    private Document document;
    private List<SiteContent> contentOfArticle;
    private String[] linkers;
    private List<String> titleSelectors,imgSelectors,articleHrefSelectors;
    private List<Selector> selectors;
    private List<Integer> absentArticles;
    private int sizeOfArticlesTable;



    public WebCrawler(){}

    public WebCrawler(String category) {


        selectors = new CategorySelectors().getAllSelectors(category);
        linkers = new String[selectors.size()];

        for(int i = 0; i < selectors.size();i++)
            linkers[i] = selectors.get(i).getDomainAdress();

    }



    public List<SiteContent> getTitleAndHref() throws IOException,IllegalArgumentException {

        Elements elements;
        contentOfArticle = new ArrayList<SiteContent>();
        sizeOfArticlesTable = 0;
        absentArticles = new ArrayList<Integer>();

        titleSelectors = new ArrayList<String>();
        imgSelectors = new ArrayList<String>();
        articleHrefSelectors = new ArrayList<String>();

        for (int i = 0; i < linkers.length; i++) {


            document = Jsoup.connect(linkers[i]).get();


            elements = document.select(selectors.get(i).getHeadSelector());

            for(Element e : elements)
            {
                if(e.select(selectors.get(i).getTitleSelector()).isEmpty() || e.select(selectors.get(i).getArticleHrefSelector()).attr("abs:href").isEmpty()
                        || e.select(selectors.get(i).getImageHref()).attr("abs:src").isEmpty())
                    continue;

                titleSelectors.add(e.select(selectors.get(i).getTitleSelector()).text());

                imgSelectors.add(e.select(selectors.get(i).getImageHref()).attr("src"));

                articleHrefSelectors.add(URLEncoder.encode(e.select(selectors.get(i).getArticleHrefSelector()).attr("abs:href"),"UTF-8"));

            }
            for (int j = 0; j < titleSelectors.size(); j++)
                contentOfArticle.add(new SiteContent(j,titleSelectors.get(j), imgSelectors.get(j), articleHrefSelectors.get(j)));

        }
        return contentOfArticle;
    }





    public String getArticleContent(String encodedUrl) throws IOException  {

        String decodedUrl,conSelector;
        try {
            decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");
        }catch (UnsupportedEncodingException ex){return "";}


        char[] decodedUrlToChar = decodedUrl.toCharArray();
        String coreDomain ="";
        for(int i = 1; i<decodedUrlToChar.length;i++)
        {
            if(decodedUrlToChar[i-1] == 'p' && decodedUrlToChar[i]=='l')
            {
                coreDomain= String.valueOf(decodedUrlToChar,0,i+2);
            }
        }
        char[] coreDomainToChar =coreDomain.toCharArray();
        for(int i = coreDomain.length()-5; i> 0; i--)
        {


            if (coreDomainToChar[i] == '.') {
                coreDomain = "http://www" + String.valueOf(coreDomainToChar, i, coreDomain.length()-i);
                System.out.println(coreDomain);
                break;
            }

        }

        conSelector = new CategorySelectors().getContentSelector(coreDomain);

        document = Jsoup.connect(decodedUrl).get();
        try
        {
            return document.select(conSelector).text();
         }catch(IllegalArgumentException ex){return "none";}



    }


}
