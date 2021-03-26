package com.lv.spring.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
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
}
