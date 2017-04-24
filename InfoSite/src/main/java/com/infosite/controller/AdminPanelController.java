package com.infosite.controller;

import com.infosite.db.CategorySelectors;
import com.infosite.objects.SiteContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminPanelController {

    private Document document;
    private List<SiteContent> testers;
    private String category,domain,title,art_href,art_cont,art_head,img_href;
    private List<String> titleSelectors,imgSelectors,articleHrefSelectors,contentOfArticle;

    @RequestMapping("/admin_panel")
    public ModelAndView test()
    {
        return new ModelAndView("admin_panel","command",new SiteContent());
    }


    @RequestMapping("/TestResults")
    public ModelAndView getTest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        category = request.getParameter("category");
        domain = request.getParameter("objects");
        title = request.getParameter("title");
        art_href= request.getParameter("article_href");
        art_cont = request.getParameter("article_content");
        art_head = request.getParameter("article_header");
        img_href = request.getParameter("img_href");

        titleSelectors = new ArrayList<String>();
        imgSelectors = new ArrayList<String>();
        articleHrefSelectors = new ArrayList<String>();
        contentOfArticle = new ArrayList<String>();
        testers = new ArrayList<SiteContent>();

        document = Jsoup.connect(domain).get();


        Elements elements = document.select(art_head);

        for(Element e : elements)
        {


            if(request.getParameter("attr")!=null)
            {
                if(e.select(title).attr(request.getParameter("attr")).equals("")  )
                    continue;
                else
                    titleSelectors.add(e.select(title).attr(request.getParameter("attr")));
            }

            else
            {
                if(e.select(title).text().equals("")  )
                    continue;
                else
                    titleSelectors.add(e.select(title).text());
            }


            imgSelectors.add(e.select(img_href).attr("abs:src"));

            articleHrefSelectors.add(URLEncoder.encode(e.select(art_href).attr("abs:href"),"UTF-8"));


        }
  for(String t : articleHrefSelectors)
        {
            document = Jsoup.connect( URLDecoder.decode(t, "UTF-8")).get();

            if(document.select(art_cont).text().isEmpty())
                contentOfArticle.add("");
            else
                contentOfArticle.add(document.select(art_cont).text());

        }

        for (int j = 0; j < titleSelectors.size(); j++) {
            if (titleSelectors.get(j) == null || articleHrefSelectors.get(j) == null || imgSelectors.get(j) == null)
                break;
            else
                testers.add(new SiteContent(j, titleSelectors.get(j), imgSelectors.get(j), articleHrefSelectors.get(j), contentOfArticle.get(j)));

        }

        if(request.getParameter("Save")!=null)
            {
                new CategorySelectors().addNewsSelectors(category,domain,title,art_href,art_cont,art_head,img_href);
            }

        ModelAndView testResults = new ModelAndView();
        testResults.addObject("testResults", testers);
        return testResults;

        }
    }