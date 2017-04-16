package com.infosite.db;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;

@Controller
public class CategoriesUserDB {

    private Connection conn;
    private PreparedStatement query;
    private Boolean[] session;



    @RequestMapping("/Account")
    public void userAccount(@ModelAttribute("login") String login,Model model){

        session = accountSpecification(login);

        model.addAttribute("Sport",session[0]);
        model.addAttribute("Culture",session[1]);
        model.addAttribute("Politic",session[2]);
        model.addAttribute("Business",session[3]);
        model.addAttribute("Science",session[4]);

    }
    @SuppressWarnings("finally")
    public Boolean[] accountSpecification(String login)
    {
        Boolean[] attributes = new Boolean[5];
        conn = ConnectorDB.setConnect();
        try{
            query = conn.prepareStatement("SELECT Sport, Culture, Politic, Business, Science FROM users"
                    + " WHERE Login= ? ");
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
