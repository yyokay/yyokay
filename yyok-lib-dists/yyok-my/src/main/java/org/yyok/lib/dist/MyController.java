package org.yyok.lib.dist;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    // final Logger logger = LogManager.getLogger(MyController.class);
    /**
     * @return
     */
    @RequestMapping("/")
    public String index() {
            System.out.println("error :用户名或密码错误");

        return "index";
    }
    /**
     * 登录处理
     * @return
     */
    @RequestMapping(value = "/login",method= RequestMethod.POST)
    public String toLogin() {
            return "login";
    }
    @RequestMapping(value = "/user/index")
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user","user");
        //记得一定要将"/"加上
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
