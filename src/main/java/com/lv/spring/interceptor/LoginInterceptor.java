package com.lv.spring.interceptor;

import com.lv.spring.annotation.PassToken;
import com.lv.spring.annotation.Permission;
import com.lv.spring.context.UserContext;
import com.lv.spring.enums.PermissionEnum;
import com.lv.spring.enums.ResultVOEnum;
import com.lv.spring.service.UserService;
import com.lv.spring.utils.JwtUtil;
import com.lv.spring.utils.PermissionCheck;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        // 通过PassToken注解放行
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 从请求头中获取token字符串并解析
        Claims claims = JwtUtil.parse(request.getHeader("token"));

        //通过Permission鉴权
        // 拦截器中注入service问题未解决。。。
//        if (method.isAnnotationPresent(Permission.class)) {
//            Permission permission = method.getAnnotation(Permission.class);
//            Integer needPermission = permission.type().getAuthority();
//            Integer selfPermission = userService.UserPermission(claims.getAudience());
//            if (selfPermission == null) return false;
//            if (selfPermission == PermissionEnum.ROOT.getAuthority()) return true;
//            if((PermissionEnum.PROHIBIT.getAuthority() & selfPermission) == PermissionEnum.PROHIBIT.getAuthority()) return false;
//            return (needPermission & selfPermission) == needPermission;
//        }

        // 已登录就直接放行
        if (claims != null) {
            // 将我们之前放到token中的userName给存到上下文对象中
            UserContext.add(claims.getSubject());
            return true;
        }

        // 走到这里就代表是其他接口，且没有登录
        // 设置响应数据类型为json（前后端分离）
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 设置响应内容，结束请求
        String info = "" +
                "{" +
                "code:" + ResultVOEnum.AUTH_FAIL.getCode() +
                ",message" + ResultVOEnum.AUTH_FAIL.getMessage() +
                ",data" + null +
                ",ok" + false +
                '}';

        out.write(info);
        out.flush();
        out.close();
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束后要从上下文对象删除数据，如果不删除则可能会导致内存泄露
        UserContext.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
