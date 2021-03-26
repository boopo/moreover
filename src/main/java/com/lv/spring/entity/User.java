package com.lv.spring.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.api.R;
import com.lv.spring.annotation.ValidExceptionCode;
import com.lv.spring.enums.ResultVOEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotNull()
    @ValidExceptionCode(type = ResultVOEnum.USERNAME_WRONG)
    private String username;

    @NotNull()
    @ValidExceptionCode(type = ResultVOEnum.PASSWORD_WRONG)
    private String password;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;  //create_time

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime; //update_time

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    private Integer permission;

}

/*
* sql
CREATE TABLE USER (
	 id BIGINT (20) NOT NULL AUTO_INCREMENT,
         username VARCHAR (100) NULL DEFAULT NULL ,
	 PASSWORD VARCHAR(100) DEFAULT NULL,
	 create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 deleted INT(10) NOT NULL DEFAULT 0,
	 permission INT(10) NOT NULL DEFAULT 4,
	PRIMARY KEY (id),
	INDEX (id, username)
)
 */
