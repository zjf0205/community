package zjf0205.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zjf0205.community.mapper.QuestionMapper;

@Controller
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;
    

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id){
        return "question";
    }
}
