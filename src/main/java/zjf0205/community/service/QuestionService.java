package zjf0205.community.service;

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
    public PaginationDtTO list(Integer page, Integer size) {
        PaginationDtTO paginationDtTO=new PaginationDtTO();
        Integer totalCount=questionMapper.count();
        paginationDtTO.setPagination(totalCount,page,size);

        if (page<1){
            page=1;
        }
        if (page>paginationDtTO.getTotalPage()){
            page=paginationDtTO.getTotalPage();
        }

        //size*(page-1)，数据库分页是 limit offset,size
        Integer offset=size*(page-1);
        //从问题表中查询到所有问题数据，并存入数据表
        List<Question>questions=questionMapper.list(offset,size);
        //定义一个QuestionDTO列表
        List<QuestionDTO>questionDTOList=new ArrayList<>();

        for (Question question:questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //将question中的信息复制到questionDTO中
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            //添加入列表
            questionDTOList.add(questionDTO);
        }
        paginationDtTO.setQuestions(questionDTOList);
        return paginationDtTO;
    }
}
