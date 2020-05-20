package zjf0205.community.dto;

import lombok.Data;
import zjf0205.community.model.User;

@Data
public class QuestionDTO {
    private  Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
