package com.infosite.controller;


import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import com.infosite.db.ResetPasswordDB;
import com.infosite.domain.UserID;
import com.infosite.other.SendNewPassword;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;

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

        if(reset.checkEmail(user.getEmail())!=0) {

            String newPass = password.passwordGenerator();
            password.sendMail(user.getEmail(),newPass);
            newPass = BaseEncoding.base32().encode(newPass.getBytes(Charsets.UTF_8));
            reset.setNewPassword( newPass,user.getEmail());


        }
        return new ModelAndView("redirect:/index");
    }

}
