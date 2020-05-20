package zjf0205.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import zjf0205.community.dto.QuestionDTO;
import zjf0205.community.mapper.UserMapper;
import zjf0205.community.model.User;
import zjf0205.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    public QuestionService questionService;


    @GetMapping("/")
    public  String index(HttpServletRequest request,Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0){
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
        }
        List<QuestionDTO> questionList= questionService.list();
        model.addAttribute("questions",questionList);

        return "index";
    }
}
