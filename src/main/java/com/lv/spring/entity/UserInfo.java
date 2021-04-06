package com.lv.spring.entity;

import com.lv.spring.annotation.ValidExceptionCode;
import com.lv.spring.enums.ResultVOEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

@Data
@Document("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Indexed
    private String UserId;

    @Indexed(unique = true)
    private String username;

    private String nickname;
    private Integer exp;
    private List<String> follow;
    private List<String> followers;
    private Integer countOfFollowers;
    private Integer countOfFollow;
    private String sex;
    private String describe;
    private Integer stars;
    private List<String> collection;
    private String back;
    @NotNull
    @ValidExceptionCode(type = ResultVOEnum.PARAMETER_ERROR)
    private String head;

}
