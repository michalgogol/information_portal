package com.infosite.crawlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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

import javax.xml.stream.events.EndElement;


public class WebCrawler {

    private Document document;
    private List<SiteContent> contentOfArticle;
    private String[] linkers;
    private List<String> titleSelectors, imgSelectors, articleHrefSelectors;
    private List<Selector> selectors;
    private List<Integer> absentArticles;
    private int sizeOfArticlesTable;


    public WebCrawler(String category) {


        selectors = new CategorySelectors().getAllSelectors(category);
        linkers = new String[selectors.size()];

        for (int i = 0; i < selectors.size(); i++)
            linkers[i] = selectors.get(i).getDomainAdress();

    }


    public List<SiteContent> getTitleAndHref() throws IOException, IllegalArgumentException {

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

            for (Element e : elements) {
                if (e.select(selectors.get(i).getTitleSelector()).isEmpty() || e.select(selectors.get(i).getArticleHrefSelector()).attr("abs:href").isEmpty())
                    continue;

                titleSelectors.add(e.select(selectors.get(i).getTitleSelector()).text());

                articleHrefSelectors.add(e.select(selectors.get(i).getArticleHrefSelector()).attr("abs:href"));

                Document document2 = Jsoup.connect(e.select(selectors.get(i).getArticleHrefSelector()).attr("abs:href")).get();

                elements = document2.select(selectors.get(i).getImageHref());

                if (!elements.attr("abs:src").isEmpty())
                    imgSelectors.add(elements.attr("abs:src"));
                else
                    imgSelectors.add("none");


            }


            for (int j = 0; j < titleSelectors.size() - 1; j++)
                contentOfArticle.add(new SiteContent(j, titleSelectors.get(j), imgSelectors.get(j), articleHrefSelectors.get(j)));

        }
        return contentOfArticle;
    }


    public String getArticleContent(String url) throws IOException {

        String conSelector;

        char[] decodedUrlToChar = url.toCharArray();
        String coreDomain = "";
        for (int i = 1; i < decodedUrlToChar.length; i++) {
            if (decodedUrlToChar[i - 1] == 'p' && decodedUrlToChar[i] == 'l') {
                coreDomain = String.valueOf(decodedUrlToChar, 0, i + 2);
            }
        }
        char[] coreDomainToChar = coreDomain.toCharArray();
        for (int i = coreDomain.length() - 5; i > 0; i--) {


            if (coreDomainToChar[i] == '.') {
                coreDomain = "http://www" + String.valueOf(coreDomainToChar, i, coreDomain.length() - i);
                break;
            }

        }

        conSelector = new CategorySelectors().getContentSelector(coreDomain);
        document = Jsoup.connect(url).get();

        try {
            return document.select(conSelector).text();
        } catch (IllegalArgumentException ex) {
            return "none";
        }


    }


}
