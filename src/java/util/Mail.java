/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hbtth
 */
public class Mail {
// email :thongbaohethong9@gmail.com
// password = "bsxu ncki whpp exlt"
//        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
//        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");


    public static String send(String emailTo) {
        final String emailFrom = "thongbaohethong9@gmail.com";
        final String password = "bsxu ncki whpp exlt";
        String message = randomOTP();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            msg.setFrom(emailFrom);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
        } catch (AddressException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            msg.setSubject("Captcha");
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            msg.setSentDate(new Date());
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        String mess = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <style>\n"
                + "        main{\n"
                + "            position: relative;\n"
                + "            background: rgb(240, 222, 222);\n"
                + "            text-align: center;\n"
                + "            height: 500px;\n"
                + "        }\n"
                + "        h1{\n"
                + "            color: #706ca2;\n"
                + "            font-size: 52px;\n"
                + "        }\n"
                + "        h4{\n"
                + "            font-size: 18px;\n"
                + "            color: darkgray;\n"
                + "        }\n"
                + "        .content-main{\n"
                + "            position: absolute;\n"
                + "            height: 60px;\n"
                + "            width: 200px;\n"
                + "            border: 1px solid black;\n"
                + "            border-radius: 5px;\n"
                + "            display: flex;\n"
                + "            justify-content: center;\n"
                + "            align-items: center;\n"
                + "            bottom: calc(50% - 30px);\n"
                + "            left: calc(50% - 100px);\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <header>\n"
                + "        captcha Code\n"
                + "    </header>\n"
                + "    <main>\n"
                + "        <h1>Welcome to Tikilazapee</h1>\n"
                + "        <h4>Use the verification code below to authentication account</h4>\n"
                + "        <div class=\"content-main\">\n"
                + message + "\n"
                + "\n"
                + "\n"
                + "        </div>\n"
                + "    </main>\n"
                + "    <footer>\n"
                + "        Thank you for using"
                + "    </footer>\n"
                + "</body>\n"
                + "</html>";
        try {
            msg.setContent(mess, "text/HTML");
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    public static String sendOTP(String emailTo) {
        final String emailFrom = "thongbaohethong9@gmail.com";
        final String password = "bsxu ncki whpp exlt";
        String message = randomOTP2();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            msg.setFrom(emailFrom);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
        } catch (AddressException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            msg.setSubject("Captcha");
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            msg.setSentDate(new Date());
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        String mess = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <style>\n"
                + "        main{\n"
                + "            position: relative;\n"
                + "            background: rgb(240, 222, 222);\n"
                + "            text-align: center;\n"
                + "            height: 500px;\n"
                + "        }\n"
                + "        h1{\n"
                + "            color: #706ca2;\n"
                + "            font-size: 52px;\n"
                + "        }\n"
                + "        h4{\n"
                + "            font-size: 18px;\n"
                + "            color: darkgray;\n"
                + "        }\n"
                + "        .content-main{\n"
                + "            position: absolute;\n"
                + "            height: 60px;\n"
                + "            width: 200px;\n"
                + "            border: 1px solid black;\n"
                + "            border-radius: 5px;\n"
                + "            display: flex;\n"
                + "            justify-content: center;\n"
                + "            align-items: center;\n"
                + "            bottom: calc(50% - 30px);\n"
                + "            left: calc(50% - 100px);\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <header>\n"
                + "        captcha Code\n"
                + "    </header>\n"
                + "    <main>\n"
                + "        <h1>Welcome to Tikilazapee</h1>\n"
                + "        <h4>Use the verification code below to reset password</h4>\n"
                + "        <div class=\"content-main\">\n"
                + message + "\n"
                + "\n"
                + "\n"
                + "        </div>\n"
                + "    </main>\n"
                + "    <footer>\n"
                + "        Thank you for using"
                + "    </footer>\n"
                + "</body>\n"
                + "</html>";
        try {
            msg.setContent(mess, "text/HTML");
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
    private static String randomOTP() {
        String templateCaptcha = "0123456789";
        StringBuilder captcha = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; ++i) {
            captcha.append(templateCaptcha.charAt(Math.abs(rand.nextInt() % 10)));
        }
        return captcha.toString();
    }

    private static String randomOTP2() {
        String templateCaptcha = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*";
        StringBuilder captcha = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 12; ++i) {
            captcha.append(templateCaptcha.charAt(Math.abs(rand.nextInt() % 70)));
        }
        return captcha.toString();
    }

    public static void main(String[] args) throws MessagingException {
        send("hbtthangtien@gmail.com");
    }
}
