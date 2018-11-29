package com.troytan.notify.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.troytan.notify.aspect.NoAuth;
import com.troytan.notify.dto.GroupDto;
import com.troytan.notify.dto.GroupUserDto;
import com.troytan.notify.dto.OauthDto;
import com.troytan.notify.dto.UserDto;
import com.troytan.notify.manager.WechatManager;
import com.troytan.notify.service.UserService;

@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=UTF-8")
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
     * @throws Exception
     */
    @PostMapping("/group")
    public String registerGroup(@RequestBody GroupDto groupDto) throws Exception {
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
    @PutMapping("/login")
    @NoAuth
    public String getSessionId(@RequestParam("code") String code) throws NoSuchAlgorithmException {
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
    @PostMapping
    public void updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
    }

    /**
     * 获取当前用户信息
     *
     * @author troytan
     * @date 2018年10月7日
     * @return
     */
    @GetMapping
    public UserDto getUser() {
        return userService.getUser();
    }

    /**
     * 批量修改群昵称
     *
     * @author troytan
     * @date 2018年7月13日
     * @param groupUsers
     * @return
     */
    @PostMapping("/groupUsers")
    public boolean updateNickname(@RequestBody List<GroupUserDto> groupUsers) {
        return userService.updateNickname(groupUsers);
    }

    /**
     * 获取群昵称列表
     *
     * @author troytan
     * @date 2018年7月13日
     * @return
     */
    @GetMapping("/groupUsers")
    public List<GroupUserDto> getGroupUsers() {
        return userService.getGroupUsers();
    }

    /**
     * 删除群用户关联
     *
     * @author troytan
     * @date 2018年7月13日
     * @param groupUserDto
     * @return
     */
    @DeleteMapping("/groupUser")
    public List<GroupUserDto> deleteGroupUser(@RequestBody GroupUserDto groupUserDto) {
        return userService.deleteGroupUser(groupUserDto);
    }

    /**
     * 群发通知
     *
     * @author troytan
     * @date 2018年10月12日
     * @return
     */
    @GetMapping("/sendMsg")
    public int sendMsg() {
        // 仅管理员调用
        if (userService.getCurrentUser() != 2) {
            return 0;
        }
        return userService.notifyUser();
    }

    /**
     * 保存formId
     *
     * @author troytan
     * @date 2018年10月12日
     * @param formIds
     */
    @PostMapping("/form")
    public void saveFormIds(@RequestBody List<String> formIds) {
        userService.uploadFormIds(formIds);
    }

}
