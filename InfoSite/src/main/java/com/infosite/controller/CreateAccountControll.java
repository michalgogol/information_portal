package com.infosite.controller;


import com.infosite.db.NewAccountDB;
import com.infosite.domain.UserID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CreateAccountControll {

    private NewAccountDB dbConnect;
    private String[] choosenCategories;



    @RequestMapping("/CreateAccountControll")
    public ModelAndView createAccount(@ModelAttribute UserID user, RedirectAttributes redirectData)
    {
        dbConnect =  new NewAccountDB(); //create new object,which provides check if login exist in database, and allows to send data
        choosenCategories = new String[5];
        setCategories(user);

        if(dbConnect.checkLoginIfExist(user.getLogin(),user.getEmail())=="")
        {
            dbConnect.setAccount(user.getLogin(),user.getPassword(),user.getEmail(),choosenCategories);
            return new ModelAndView("redirect:/index");
        }
        else
        {
            redirectData.addFlashAttribute("Error",1);
            return new ModelAndView("redirect:/CreateAccount");
        }
    }

    public void setCategories(UserID u)
    {
        choosenCategories[0]=u.getSport();
        choosenCategories[1]=u.getCulture();
        choosenCategories[2]=u.getPolitic();
        choosenCategories[3]=u.getBusiness();
        choosenCategories[4]=u.getScience();
    }

}
