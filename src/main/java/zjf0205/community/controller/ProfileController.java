package zjf0205.community.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import zjf0205.community.mapper.UserMapper;
import zjf0205.community.model.User;
import zjf0205.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//个人资料
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;


    @GetMapping("/profile/{action}")//通过action动态的切换路径
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "3") Integer size,
                          Model model){//传值给profile页面

        Cookie[] cookies = request.getCookies();
        User user=null;
        if (cookies!=null&&cookies.length!=0){
            for(Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user=userMapper.findByToken(token);
                    if (user!=null){//将数据放到session
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        if (user==null){
            return "redirect:/";
        }


        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else  if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PageInfo profilePageInfo=questionService.listByUserId(user.getId(),page,size);
        model.addAttribute("profilePageInfo",profilePageInfo);


        return "profile";
    }
}
