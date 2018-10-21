package com.example.dbtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping(value = "/showMyLoginPage")
    public String login() {
        return "plain-login";
    }

    @GetMapping(value = "/access-denied")
    public ModelAndView showAccessDenied(ModelAndView mav) {

        mav.addObject("title", "Access-Denied");
        mav.setViewName("access-denied");
        return mav;

    }

}
