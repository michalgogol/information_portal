package com.infosite.other;



import com.sun.mail.smtp.SMTPMessage;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;



public class SendNewPassword {

    private Properties properties;
    private Session session;
    private SMTPMessage message;

    public void sendMail(String mail,String password)
    {
        properties = new Properties();
        properties.put("mail.smtp.host","smtp.----.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","805");

        session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("------","------");
            }
                });



        try
        {
            message= new SMTPMessage(session);
            message.setFrom(new InternetAddress("-----@----.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail));
            message.setSubject("Reset Password");
            message.setText("Your new password : "+ password);
            message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS);
            Transport.send(message);
        }catch (MessagingException mex){mex.printStackTrace();}

    }

    public String passwordGenerator()
    {
        char [] alphabet = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
        String newPassword= "";

        for(int i=0; i<9;i++)
        {
            int rand = (int)(Math.random()*(alphabet.length-1));
            newPassword = newPassword+alphabet[rand];
        }

        return newPassword;
    }


}
