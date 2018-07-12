package com.troytan.notify.controller;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.troytan.notify.aspect.NoAuth;
import com.troytan.notify.dto.GroupDto;
import com.troytan.notify.dto.OauthDto;
import com.troytan.notify.dto.UserDto;
import com.troytan.notify.manager.WechatManager;
import com.troytan.notify.service.UserService;

@Controller
@Path("/user")
@Consumes("application/json;charset=utf-8")
@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
public class UserController {

    @Autowired
    private UserService   userService;
    @Autowired
    private WechatManager wechatManager;

    /**
     * 记录群组信息
     *
     * @author troytan
     * @date 2018年7月10日
     * @param groupDto
     * @return
     */
    @POST
    @Path("/group")
    public String registerGroup(GroupDto groupDto) {
        // 获取openID与sessionKey
        // OauthDto oauthDto = wechatManager.requestOauth(groupDto.getUserCode());

        return userService.registerGroup(groupDto);
    }

    /**
     * 用户登录
     *
     * @author troytan
     * @date 2018年7月10日
     * @param userDto
     * @return
     * @throws NoSuchAlgorithmException
     */
    @PUT
    @Path("/login")
    @NoAuth
    public String getSessionId(@QueryParam("code") String code) throws NoSuchAlgorithmException {
        OauthDto oauthDto = wechatManager.requestOauth(code);

        return userService.logUser(oauthDto);
    }

    /**
     * 更新用户信息
     *
     * @author troytan
     * @date 2018年7月11日
     * @param userDto
     */
    @POST
    public void updateUser(UserDto userDto) {
        userService.updateUser(userDto);
    }
}
