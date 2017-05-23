package com.infosite.other;


import com.infosite.crawlers.WebCrawler;
import com.infosite.db.ArticlesInDB;
import com.infosite.objects.SiteContent;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.*;

@WebListener
public class CheckNews implements ServletContextListener {

    private Map currentTitleAndHref = new HashMap();
    private Map currentContent = new HashMap();

    public Map getCurrentTitleAndHref() {
        return currentTitleAndHref;
    }

    public Map getCurrentContent() {
        return currentContent;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        Thread temp = new Thread(() -> {
            String[] categories = {"Sport","Business","Politic","Science","Culture"};
            currentTitleAndHref = new HashMap<String, SiteContent>();
            currentContent = new HashMap<String, String>();
            ArticlesInDB articlesInDB = new ArticlesInDB();
            List<SiteContent> titleHref;
            try {
                while (true) {

                    WebCrawler crawler;

                    for (String t : categories) {

                        crawler = new WebCrawler(t);
                        titleHref = crawler.getTitleAndHref();
                        List<String> content = new ArrayList<>();
                        String oneArticle;
                        for (Iterator<SiteContent> it = titleHref.iterator(); it.hasNext(); ) {
                            oneArticle = crawler.getArticleContent(it.next().getArticle_href());

                            if (!oneArticle.equals("")) {
                                content.add(oneArticle);
                            } else
                                it.remove();

                        }

                        currentTitleAndHref.put(t, titleHref);
                        currentContent.put(t, content);

                        articlesInDB.setArticles(titleHref, content, t);
                        articlesInDB.removerOldArticles(titleHref);
                    }


                    Thread.sleep(600000);
                }

            } catch (InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
        });

        temp.start();


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}