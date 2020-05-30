package zjf0205.community.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjf0205.community.dto.PaginationDtTO;
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
    public PageInfo list(Integer page, Integer size) {
        List<QuestionDTO>questionDTOList=new ArrayList<>();
        Page<Object> p=PageHelper.startPage(page,size);
        List<Question> questions=questionMapper.findAll();
        for (Question question:questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //将question中的信息复制到questionDTO中
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            //添加入列表
            questionDTOList.add(questionDTO);
        }
        PageInfo pageInfo=new PageInfo(p.getResult());
        pageInfo.setList(questionDTOList);

//        PaginationDtTO paginationDtTO=new PaginationDtTO();
//        paginationDtTO.setPagination((int) pageInfo.getTotal(),page,size);
//        System.out.println(pageInfo.getPages());
//        paginationDtTO.setQuestions(pageInfo.getList());
        return pageInfo;
    }
}
