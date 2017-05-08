package com.infosite.db;

import com.infosite.domain.UserID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@Controller
public class userDB {

    private Connection conn;
    private PreparedStatement query;


    @ResponseBody
    @RequestMapping(value = "/userDAO", method = RequestMethod.POST)
    public ModelAndView temp(@ModelAttribute UserID user, RedirectAttributes redirectLogin, HttpServletResponse response) {

        if(checkPassword(user.getLogin(),user.getPassword())==true)
        {
            redirectLogin.addFlashAttribute("login",user.getLogin());

       //     response.addCookie(new Cookie("login",user.getLogin()));
            return new ModelAndView("redirect:/Account");

        }
        else
            return new ModelAndView("redirect:/index");

    }
    public Boolean checkPassword(String login,String pass) //jeśli zwraca pusty string to znaczy, że nie ma konta o takim loginie
    {
        String passy="";
        conn = ConnectorDB.setConnect();
        if(pass == "")
            return false;

        try{

            Statement state = conn.createStatement();
            state.execute("USE infoPortal");

            query = conn.prepareStatement("SELECT Password FROM users WHERE login= ? ");
            query.setString(1, login);
            ResultSet res = query.executeQuery();

            while(res.next())
            {
                passy=res.getString(1);
            }

            if(passy.equals(pass)==true)
                return true;
            else
                return false;

        }catch(Exception ex){ex.printStackTrace();}

        return false;
    }


}
