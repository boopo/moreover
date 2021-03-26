package com.lv.spring.annotation;

import com.baomidou.mybatisplus.extension.api.R;
import com.lv.spring.enums.ResultVOEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidExceptionCode {
    ResultVOEnum type() default ResultVOEnum.SUCCESS;
}
