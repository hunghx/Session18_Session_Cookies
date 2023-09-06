package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.User;
import ra.service.IUserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(Model model, HttpSession session, @RequestParam String username, @RequestParam String password){
        User user =  userService.login(username,password,session);
        if(user==null){
            model.addAttribute("err","Tài khoản hoặc mật khẩu không chính xác!");
            return "login";
        }
        return "home";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        userService.logout(session);
        return "login";
    }
}
