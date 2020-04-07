package cn.cqu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model mv) {
        String e = (String) request.getAttribute("shiroLoginFailure");
        if (e != null) {
            if (e.contains("org.apache.shiro.authc.UnknownAccountException")) {
                mv.addAttribute("msg", "账号不存在");
            } else if (e.contains("org.apache.shiro.authc.IncorrectCredentialsException")) {
                mv.addAttribute("msg", "密码错误");
            }
        }
        return "login";
    }

}
