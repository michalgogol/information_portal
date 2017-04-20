package com.infosite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class IndexController {


    @RequestMapping("/index")
    public void showIndex(){}



    @RequestMapping("/CreateAccount")
    public void newAccount(){

    }

    @RequestMapping("/end")
    public ModelAndView endSession(HttpServletResponse response){

        response.addCookie(new Cookie("userid",""));
        return new ModelAndView("redirect:/index");
    }


}
