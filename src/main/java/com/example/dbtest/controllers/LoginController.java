package com.example.dbtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ログイン画面
 */
@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage") // Anotationのvalueは省略可
    public String login() {
        return "plain-login";
    }

    @GetMapping("/access-denied")
    public ModelAndView showAccessDenied(ModelAndView mav) {

        mav.addObject("title", "Access-Denied");
        mav.setViewName("access-denied");
        return mav;

    }

}
