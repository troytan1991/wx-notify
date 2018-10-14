package com.troytan.notify.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troytan.notify.dto.UserSessionDto;
import com.troytan.notify.exception.UnauthException;
import com.troytan.notify.service.UserService;

@Component
@Aspect
public class AuthAspect {

    @Autowired
    private UserService        userService;
    @Autowired
    private HttpServletRequest request;

    @Before("within(com.troytan.notify.controller..*) && !@annotation(com.troytan.notify.aspect.NoAuth)")
    public void checkRequest(){
        // 生产用
        String sessionId = request.getParameter("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new UnauthException("未找到sessionId");
        }
        UserSessionDto user = userService.getSession(sessionId);
        if (user == null) {
            throw new UnauthException("用户认证过期");
        }
        userService.setCurrentUser(user);

        // 测试用
        // userService.setCurrentUser(1L);
    }
}
