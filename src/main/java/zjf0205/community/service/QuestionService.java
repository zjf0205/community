package zjf0205.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjf0205.community.dto.QuestionDTO;
import zjf0205.community.mapper.QuestionMapper;
import zjf0205.community.mapper.UserMapper;
import zjf0205.community.model.Question;
import zjf0205.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private  QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
   //查询问题表的功能
    public List<QuestionDTO> list() {
        //从问题表中查询到所有问题数据，并存入数据表
        List<Question>questions=questionMapper.list();
        //定义一个QuestionDTO列表
        List<QuestionDTO>questionDTOList=new ArrayList<>();
        for (Question question:questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //将question中的信息复制到questionDTO中
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            //添加入表
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
