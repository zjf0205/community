package zjf0205.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//个人资料
@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")//通过action动态的切换路径
    public String profile(@PathVariable(name = "action") String action,
                          Model model){//传值给profile页面
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else  if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
