package com.infosite.db;

import com.google.common.io.BaseEncoding;
import com.infosite.objects.UserID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.UUID;

@Controller
public class userDB {

    private Connection conn;
    private PreparedStatement query;
    private Statement state;
    private String uuid;


    @ResponseBody
    @RequestMapping(value = "/userDB", method = RequestMethod.POST)
    public ModelAndView temp(@ModelAttribute UserID user, HttpServletResponse response) {

        if(checkPassword(user.getLogin(),user.getPassword())==true)
        {
            setUuid(user.getLogin());
            response.addCookie(new Cookie("userid",uuid));
            response.addCookie(new Cookie("userLogin",user.getLogin()));
            return new ModelAndView("redirect:/Account");

        }
        else
            return new ModelAndView("redirect:/index");

    }
    public Boolean checkPassword(String login,String pass) //jeśli zwraca pusty string to znaczy, że nie ma konta o takim loginie
    {
        String cryptedPassword="";
        conn = ConnectorDB.setConnect();
        if(pass == "")
            return false;

        try{

            state = conn.createStatement();
            state.execute("USE infoPortal");

            query = conn.prepareStatement("SELECT Password FROM users WHERE login= ? ");
            query.setString(1, login);
            ResultSet res = query.executeQuery();

            while(res.next())
            {
                cryptedPassword=res.getString(1);
            }
            byte[] encodedPasswordInBytes = BaseEncoding.base32().decode(cryptedPassword);
            String encodedPassword = new String(encodedPasswordInBytes);

            if(encodedPassword.equals(pass)==true)
                return true;
            else
                return false;

        }catch(Exception ex){ex.printStackTrace();}

        return false;
    }
    public void setUuid(String login)
    {

        try {
            uuid= UUID.randomUUID().toString();
            query = conn.prepareStatement("UPDATE users SET uuid = ? WHERE Login = ?");
            query.setString(1, uuid);
            query.setString(2,login);
            query.executeUpdate();
        }catch (SQLException ex){ex.printStackTrace();}

    }




}
