package com.lv.spring.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lv.spring.annotation.ValidExceptionCode;
import com.lv.spring.entity.base.BaseMongoEntity;
import com.lv.spring.enums.ResultVOEnum;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("active_post")
public class Post extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    @Indexed
    private String publisher;

    private String head;

    @NotNull
    @ValidExceptionCode(type = ResultVOEnum.PARAMETER_ERROR)
    private String title;
    @NotNull
    @ValidExceptionCode(type = ResultVOEnum.PARAMETER_ERROR)
    private String startTime;
    @NotNull
    @ValidExceptionCode(type = ResultVOEnum.PARAMETER_ERROR)
    private String cutoffTime;
    @NotNull
    @ValidExceptionCode(type = ResultVOEnum.PARAMETER_ERROR)
    private String location;
    @NotNull
    @ValidExceptionCode(type = ResultVOEnum.PARAMETER_ERROR)
    private String outline;
    @NotNull
    @ValidExceptionCode(type = ResultVOEnum.PARAMETER_ERROR)
    private String detail;

    private Integer star;
    private List<String> starList;
    private Integer collection;

    private List<String> thumbnailImage;
    private List<String> originalImage;


}
