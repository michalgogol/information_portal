package com.infosite.controller;


import com.infosite.db.ResetPasswordDB;
import com.infosite.domain.UserID;
import com.infosite.other.SendNewPassword;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgottenPasswordControll {

    private SendNewPassword password ;
    private ResetPasswordDB reset;
    private SendNewPassword temp;

    @RequestMapping("forgottenPassword")
    public void forggottenPassword(){

    }

    @RequestMapping("/forgottenPasswordControll")
    public ModelAndView receivePassword(@ModelAttribute UserID user)
    {
        password = new SendNewPassword();
        reset = new ResetPasswordDB();

        System.out.print(user.getEmail());

        if(reset.checkEmail(user.getEmail())!=0) {

            String tempPass = password.passwordGenerator();
            reset.setNewPassword( tempPass,user.getEmail());
            password.sendMail(user.getEmail(),tempPass);

        }
        return new ModelAndView("redirect:/index");
    }

}
