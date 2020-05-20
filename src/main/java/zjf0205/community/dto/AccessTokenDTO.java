package zjf0205.community.dto;

import lombok.Data;

/*
第二部post传参数表
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
