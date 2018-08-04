package com.troytan.notify.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ClientErrorException;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troytan.notify.dto.UserSessionDto;
import com.troytan.notify.service.UserService;

@Component
@Aspect
public class AuthAspect {

    @Autowired
    private UserService        userService;
    @Autowired
    private HttpServletRequest request;

    @Before("within(com.troytan.notify.controller..*) && !@annotation(com.troytan.notify.aspect.NoAuth)")
    public void checkRequest() {
        // 生产用
        String sessionId = request.getParameter("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new ClientErrorException(401);
        }
        UserSessionDto user = userService.checkSessionId(sessionId);
        if (user == null) {
            throw new ClientErrorException(401);
        }
        userService.setCurrentUser(user);

        // 测试用
        // userService.setCurrentUser(1L);
    }
}
