package zjf0205.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zjf0205.community.mapper.UserMapper;
import zjf0205.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public  String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user=userMapper.findByToken(token);
                if (user!=null){//将数据放到session
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }


        return "index";
    }
}
