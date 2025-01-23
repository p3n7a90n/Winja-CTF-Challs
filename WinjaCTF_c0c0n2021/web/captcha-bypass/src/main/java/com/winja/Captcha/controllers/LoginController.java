package com.winja.Captcha.controllers;

import com.winja.Captcha.models.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import static com.winja.Captcha.Util.Constants.PASSWORD;
import static com.winja.Captcha.Util.Constants.USERNAME;

@Controller
public class LoginController {


    @GetMapping("/")
    public String loginPage(Model model)
    {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm",loginForm);
        return "login";
    }
    @PostMapping("/submit")
    public String onSubmitLogin(@ModelAttribute("loginForm") LoginForm loginForm,Model model, HttpSession session)
    {
        if (loginForm.getUserName()==null || loginForm.getUserName().equals(""))
        {
            loginForm.setCaptcha("");
            model.addAttribute("message", "User Name is required");
            return "login";
        }

        if (loginForm.getPassword()==null || loginForm.getPassword().equals(""))
        {
            loginForm.setCaptcha("");
            model.addAttribute("message", "Password is required");
            return "login";
        }

        String captcha=(String)session.getAttribute("captcha");
        if(captcha==null || (captcha!=null && !captcha.equals(loginForm.getCaptcha()))){
            loginForm.setCaptcha("");
            model.addAttribute("message", "Please verify that you are not a robot");
            return "login";
        }

        if(loginForm.getUserName().equals(USERNAME) && loginForm.getPassword().equals(PASSWORD)){
            System.out.println("UserName and password matches");
            model.addAttribute("loginId", loginForm.getUserName());
            return "home";

        }
        else{
            loginForm.setCaptcha("");
            model.addAttribute("message","Username and password combination doesn't match");
            return "login";
        }

    }

}
