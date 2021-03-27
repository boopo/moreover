package com.lv.spring.entity;

import com.lv.spring.annotation.ValidExceptionCode;
import com.lv.spring.entity.base.BaseMongoEntity;
import com.lv.spring.enums.ResultVOEnum;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;

@Data
@Document("comment")
public class CommentPost extends BaseMongoEntity {
    private static final long serialVersionUID = 1L;

    @Indexed
    private String postId;

    @Indexed
    private String parentId;

    private Integer star;


    private List starList;

    private String publisher;

    @NotNull
    @ValidExceptionCode(type = ResultVOEnum.PARAMETER_ERROR)
    private String message;

}
