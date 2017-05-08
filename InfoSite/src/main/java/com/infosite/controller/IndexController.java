package com.infosite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;


@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView goToIndex(){
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping("/index")
    public void showIndex(){}



    @RequestMapping("/CreateAccount")
    public void newAccount(){

    }

    @RequestMapping("/end")
    public ModelAndView endSession(){

        Cookie cookie = new Cookie("login","");
        cookie.setMaxAge(0);
        return new ModelAndView("redirect:/index");
    }


}
