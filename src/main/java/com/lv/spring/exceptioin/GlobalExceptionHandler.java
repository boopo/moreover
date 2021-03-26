package com.lv.spring.exceptioin;

import com.lv.spring.annotation.ValidExceptionCode;
import com.lv.spring.vo.ResultVO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;

@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO error(Exception e) {
        e.printStackTrace();
        return ResultVO.fail();
    }

    //自定义API异常处理
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResultVO error(ApiException e) {
        e.printStackTrace();
        return ResultVO.fail();
    }

    //参数校验异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        // 从异常错误对象中获得错误信息
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // 参数的Class对象，等下好通过字段名称获取Field对象
        Class<?> parameterType = e.getParameter().getParameterType();

        // 拿到错误的字段名称
        String fieldName = e.getBindingResult().getFieldError().getField();
        Field field = parameterType.getDeclaredField(fieldName);

        // 获取Field对象上的自定义注解
        ValidExceptionCode annotation = field.getAnnotation(ValidExceptionCode.class);

        // 有注解的话就返回注解的响应信息
        if (annotation != null) {
            return ResultVO.build(annotation.type().getCode(), annotation.type().getMessage());
        }

        // 没有注解就提取错误提示信息进行返回统一错误码
        return ResultVO.fail();
    }



}
