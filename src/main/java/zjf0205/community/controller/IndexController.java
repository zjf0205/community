package zjf0205.community.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zjf0205.community.service.QuestionService;


@Controller
public class IndexController {
    @Autowired
    public QuestionService questionService;


    @GetMapping("/")
    public  String index(Model model,
                         @RequestParam(name="page",defaultValue = "1") Integer page,
                         @RequestParam(name="size",defaultValue = "3") Integer size
                          ) {

        PageInfo pageInfo =questionService.list(page,size);
        model.addAttribute("pageInfo",pageInfo);

        return "index";
    }
}
