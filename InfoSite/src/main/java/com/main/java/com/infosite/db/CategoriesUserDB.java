package com.infosite.db;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.CollationKey;

@Controller
public class CategoriesUserDB {

    private Connection conn;
    private PreparedStatement query;
    private Boolean[] session;



    @RequestMapping("/Account")
    public ModelAndView userAccount(@CookieValue("userid") String uuid, Model model, HttpServletRequest request){


        if(uuid.equals("") || uuid == null)
            return new ModelAndView("redirect: /index");

        session = accountSpecification(uuid);
        model.addAttribute("Sport",session[0]);
        model.addAttribute("Culture",session[1]);
        model.addAttribute("Politic",session[2]);
        model.addAttribute("Business",session[3]);
        model.addAttribute("Science",session[4]);

        return new ModelAndView();

    }


    public Boolean[] accountSpecification(String login)
    {
        Boolean[] attributes = new Boolean[5];
        conn = ConnectorDB.setConnect();
        try{
            query = conn.prepareStatement("SELECT Sport, Culture, Politic, Business, Science FROM users"
                    + " WHERE uuid= ? ");
            query.setString(1, login);
            ResultSet res = query.executeQuery();

            while(res.next())
            {
                attributes[0] = res.getBoolean("Sport");
                attributes[1] = res.getBoolean("Culture");
                attributes[2] = res.getBoolean("Politic");
                attributes[3] = res.getBoolean("Business");
                attributes[4] = res.getBoolean("Science");

            }
        }catch(SQLException ex){ex.printStackTrace();}
        finally{

            return attributes;

        }


    }



}
