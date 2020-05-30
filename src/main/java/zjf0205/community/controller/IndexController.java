package zjf0205.community.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zjf0205.community.dto.PaginationDtTO;
import zjf0205.community.mapper.UserMapper;
import zjf0205.community.model.User;
import zjf0205.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    public QuestionService questionService;


    @GetMapping("/")
    public  String index(HttpServletRequest request, Model model,
                         @RequestParam(name="page",defaultValue = "1") Integer page,
                         @RequestParam(name="size",defaultValue = "5") Integer size
                          ) {
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
         PageInfo pageInfo =questionService.list(page,size);
        model.addAttribute("pageInfo",pageInfo);

        return "index";
    }
}
