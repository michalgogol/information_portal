package com.infosite.controller;


import com.infosite.crawlers.WebCrawler;

import com.infosite.domain.SiteContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@Controller
@SessionAttributes("news")
public class NewsController {

    private List<SiteContent> info;
    private WebCrawler crawler;

    @RequestMapping("/newsloader")
    public void setLoader()
    {
    }

    @RequestMapping("/News")
    public ModelAndView setNewsTitles(@CookieValue("category") String categoryValue)
    {
        crawler = new WebCrawler(categoryValue);
        ModelAndView modeler = new ModelAndView();

        long timer = System.currentTimeMillis();

        try {
            info = crawler.getTitleAndHref();
        }catch (IOException ex){ex.printStackTrace();}

        timer = System.currentTimeMillis() - timer;
        System.out.println(timer);

        modeler.addObject("news",info);
        return modeler;

    }

}
