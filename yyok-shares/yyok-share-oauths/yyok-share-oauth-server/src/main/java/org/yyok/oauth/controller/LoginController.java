package org.yyok.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class LoginController {

    /*@RequestMapping(value = "/login")
    public String login() {

        return "login";
    }*/
    @RequestMapping(value = "/home")
    public String home() {
        System.err.println("123");
        return "home";
    }

    @GetMapping("/user")
    public @ResponseBody Principal user(Principal user){
        return user;
    }

    /**
     * @see org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter
     * @param model
     * @param request
     * @param error
     * @return
     */
    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request, @RequestParam(value = "error", required = false) String error) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (token != null) {
            model.addAttribute("tokenName",token.getParameterName());
            model.addAttribute("token",token.getToken());
        }
        if (error != null) {
            model.addAttribute("error", "用户名或密码错误");
        }
        return "login";
    }

}