package com.infosite.controller;

import com.infosite.db.CategorySelectors;
import com.infosite.domain.SiteContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class AdminPanelController {

    private Document document;
    private List<SiteContent> testers;
    private String category,domain,title,art_href,art_cont,art_head,img_href;

    @RequestMapping("/admin_panel")
    public ModelAndView test()
    {
        return new ModelAndView("admin_panel","command",new SiteContent());
    }


    @RequestMapping("/TestResults")
    public ModelAndView getTest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        category = request.getParameter("category");
        domain = request.getParameter("domain");
        title = request.getParameter("title");
        art_href= request.getParameter("article_href");
        art_cont = request.getParameter("article_content");
        art_head = request.getParameter("article_header");
        img_href = request.getParameter("img_href");

        try {

            document = Jsoup.connect(request.getParameter("domain")).get();

            } catch (IllegalArgumentException ex) {

            return new ModelAndView().addObject("testResults", testers = new ArrayList<SiteContent>());
        }

        Elements elements = document.select(art_href);
        String[] separatedSelectors = art_cont.split(Pattern.quote("&&")); //allows to use many selectors refers to the same content
        testers = new ArrayList<SiteContent>();

        int iterator = 0;
        for (int i = 0; i < (elements.size()) / 2; i++) {

            if ( elements.get(i).attr("abs:href").equals(""))
                continue;

            else {

                testers.add(new SiteContent());
                testers.get(iterator).setArticle_href(elements.get(i ).attr("abs:href"));
                iterator++;

            }

        }

        for (int i = 0; i < testers.size() ; i++) {

            try {

                document = Jsoup.connect(testers.get(i).getArticle_href()).get();

            } catch (IllegalArgumentException ex) {return new ModelAndView().addObject("testResults", testers);}


            if(document.select(title).text().length()>0) {

                String[] temp =(document.select(title).text().split(Pattern.quote("-")));
                testers.get(i).setTitle(temp[0]);

            }

            for(String selector : separatedSelectors)
            {

                if (document.select(selector).text().length() > 0)
                    testers.get(i).setArticle_content(document.select(selector).text().substring(0, 15) + "...");
                else
                    continue;

            }

            if(document.select(art_head).text().length()>0)
                    testers.get(i).setArticle_header(document.select(art_head).text().substring(0, 10) + "...");


            if(document.select(img_href).attr("src").length()>0)
                   testers.get(i).setImg_Href(document.select(img_href).attr("src").substring(0, 10) + "...");

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