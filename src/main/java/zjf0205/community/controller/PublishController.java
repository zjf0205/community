package zjf0205.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zjf0205.community.mapper.QuestionMapper;
import zjf0205.community.mapper.UserMapper;
import zjf0205.community.model.Question;
import zjf0205.community.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    public QuestionMapper questionMapper;


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title" ,required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description==null||description==""){
            model.addAttribute("error","输入不能为空");
            return "publish";
        }
        if (tag==null||tag ==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
       User user= (User) request.getSession().getAttribute("user");

        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
       question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insert(question);

        return "redirect:/";//成功重定向到首页
    }
}
