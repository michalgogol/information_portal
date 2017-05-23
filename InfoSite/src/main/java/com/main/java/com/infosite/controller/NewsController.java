package com.infosite.controller;


import com.infosite.crawlers.WebCrawler;

import com.infosite.db.ArticlesInDB;
import com.infosite.db.CommentsDB;
import com.infosite.objects.SiteContent;
import com.infosite.other.CheckNews;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes("news")
public class NewsController {

    private List<SiteContent> info;
    private WebCrawler crawler;
    private CheckNews news;

    @RequestMapping("/newsloader")
    public void setLoader() {
    }

    @RequestMapping("/News")
    public ModelAndView setNewsTitles(@CookieValue("category") String categoryValue) {

        ModelAndView modeler = new ModelAndView();
        ArticlesInDB art = new ArticlesInDB();

        modeler.addObject("news", art.getAricles(categoryValue));
        return modeler;

    }


    @RequestMapping(value = "/newcontent", method = RequestMethod.GET)
    public ModelAndView sendContentOfArticle(HttpServletRequest request) {
        ModelAndView container = new ModelAndView();
        ArticlesInDB content = new ArticlesInDB();
        container.addObject("content", content.getContent(request.getParameter("url")));

        return container;

    }

    @RequestMapping(value = "/testajax")
    @ResponseBody
    public void testAjax(HttpServletResponse response, HttpServletRequest request) throws IOException {

        request.setCharacterEncoding("UTF-8");
        String comment = request.getParameter("comment");


        String login = request.getParameter("login");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        CommentsDB commentsDB = new CommentsDB();


        commentsDB.addComment(comment,login,articleId);
        String returnComment = login +": &#13;&#10;"+ comment;
        response.setContentType("text/plain");
        response.getWriter().write(returnComment);
    }


}
