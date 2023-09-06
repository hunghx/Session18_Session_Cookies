package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
// không định nghĩa requestmappring thì đường dẫn mặc đinhj mapping của Homecontroller là ("/")
public class HomeController {

    @Autowired
    private HttpSession httpSession;
    @GetMapping
    public  String home(@ModelAttribute("cart") Object cart, HttpServletResponse response){
//        để thiết lập 1 cookie
        // Khởi tạo đối tượng cookie
        Cookie cookie = new Cookie("username", "hunghx123");
        // thiết lập thời gian sống của cookie (s)
        cookie.setMaxAge(1*60*60); // = 1 giờ
        response.addCookie(cookie);
        return "home";
    }
    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies
             ) {
            if(c.getName().equals("username")){
                // có tồn tại cookie đã set ở trên
                model.addAttribute("username",c);
            }
        }
        return "list";
    }

    // bài toán kiểm tra xem ngườ dùng truy cập 1 đường dẫn bào nhiều lần
    @GetMapping("/count")
    public String count(@CookieValue(value = "count",defaultValue = "0") Long count, HttpServletResponse response, Model model){
//        Cookie[] cookies = request.getCookies();
        count++;
        Cookie cookie=new Cookie("count",count.toString());
        // kiểm tra xem có tồn tại giá trị cookie có tên là count hay không
//        for (Cookie c:cookies
//             ) {
//            if (c.getName().equals("count")){
//                cookie=c;
//            }
//        }
        model.addAttribute("count",count);
        response.addCookie(cookie);
        return "count";
    }
        @GetMapping("/clearCookies")
    public String clear(@CookieValue String count,HttpServletResponse response){
        Cookie cookie = new Cookie("count",count);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
    @GetMapping("/session")
    public String getSession(HttpSession session){
        return "redirect:/";
    }

}
