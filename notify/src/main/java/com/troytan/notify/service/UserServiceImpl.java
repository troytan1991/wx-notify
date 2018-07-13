package com.troytan.notify.service;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.troytan.notify.constant.Constant;
import com.troytan.notify.domain.GroupUser;
import com.troytan.notify.domain.Notify;
import com.troytan.notify.domain.User;
import com.troytan.notify.dto.GroupDto;
import com.troytan.notify.dto.GroupUserDto;
import com.troytan.notify.dto.OauthDto;
import com.troytan.notify.dto.UserDto;
import com.troytan.notify.dto.UserSessionDto;
import com.troytan.notify.repository.GroupUserMapper;
import com.troytan.notify.repository.NotifyMapper;
import com.troytan.notify.repository.UserMapper;
import com.troytan.notify.util.AESUtils;
import com.troytan.notify.util.SHAUtils;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger         log        = LoggerFactory.getLogger(UserServiceImpl.class);

    private ThreadLocal<UserSessionDto> userHolder = new ThreadLocal<>();
    private Map<String, UserSessionDto> map        = new ConcurrentHashMap<String, UserSessionDto>();

    @Autowired
    private NotifyMapper                notifyMapper;
    @Autowired
    private GroupUserMapper             groupUserMapper;
    @Autowired
    private UserMapper                  userMapper;

    /**
     * 关联群组-通知，群组-用户
     *
     * @author troytan
     * @date 2018年7月10日
     * @param oauthDto
     * @param groupDto
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.UserService#registerGroup(com.troytan.notify.dto.OauthDto,
     * com.troytan.notify.dto.GroupDto)
     */
    @Override
    @Transactional
    public String registerGroup(GroupDto groupDto) {
        UserSessionDto sessionDto = userHolder.get();
        String result = "";
        // 解密群ID
        try {
            result = AESUtils.decrypt(groupDto.getEncryptedData(), groupDto.getIv(), sessionDto.getSessionKey());
        } catch (Exception e) {
            log.error("解密数据失败", e);
        }
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        String groupId = jsonObject.get("openGId").getAsString();

        // 进行通知-群ID关联
        Notify dbNotify = notifyMapper.selectByPrimaryKey(groupDto.getNotifyId());
        if (dbNotify.getGroupId() == null) {
            dbNotify.setGroupId(groupId);
            notifyMapper.updateByPrimaryKey(dbNotify);
        }

        // 群--用户关联
        GroupUser groupUser = groupUserMapper.selectByPrimaryKey(groupId, sessionDto.getOpenId());
        if (groupUser == null) {
            groupUser = new GroupUser();
            groupUser.setGroupId(groupId);
            groupUser.setOpenId(sessionDto.getOpenId());
            groupUser.setStatus(Constant.GROUP_STATUS_ENABLE);
            groupUser.setCreateBy(1);
            groupUserMapper.insert(groupUser);
        }

        return groupId;
    }

    @Override
    public void setCurrentUser(UserSessionDto userSessionDto) {
        userHolder.set(userSessionDto);
    }

    @Override
    public Integer getCurrentUser() {
        return userHolder.get().getUserId();
        // return 1;
    }

    /**
     * 记录用户，并且返回自定义登录态
     *
     * @author troytan
     * @date 2018年5月17日
     * @param oauthDto
     * @param userVo
     * @return
     * @throws NoSuchAlgorithmException (non-Javadoc)
     * @see com.troytan.ymcake.service.UserService#logUser(com.troytan.ymcake.dto.OauthDto,
     * com.troytan.ymcake.vo.UserVo)
     */
    @Override
    public String logUser(OauthDto oauthDto) throws NoSuchAlgorithmException {
        User user = userMapper.selectByOpenId(oauthDto.getOpenid());
        if (user == null) {
            // 插入user表
            user = new User();
            user.setOpenId(oauthDto.getOpenid());
            user.setCreateBy(1);
            userMapper.insert(user);
        }
        String shaKey = SHAUtils.getSha1(oauthDto.getOpenid() + oauthDto.getSession_key());

        // 移除当前用户session数据
        Iterator<Entry<String, UserSessionDto>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, UserSessionDto> entry = iterator.next();
            if (entry.getValue().getUserId().equals(user.getUserId())) {
                map.remove(entry.getKey());
            }
        }

        // 重新存入用户信息到缓存中
        map.put(shaKey, new UserSessionDto(user.getUserId(), oauthDto.getOpenid(), oauthDto.getSession_key()));
        return shaKey;
    }

    @Override
    public UserSessionDto checkSessionId(String sessionId) {

        return map.get(sessionId);
    }

    /**
     * 更新用户信息
     *
     * @author troytan
     * @date 2018年7月11日
     * @param userDto (non-Javadoc)
     * @see com.troytan.notify.service.UserService#updateUser(com.troytan.notify.dto.UserDto)
     */
    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        User user = new User();
        user.setUserId(getCurrentUser());
        user.setAvatarUrl(userDto.getAvatarUrl());
        user.setGender(userDto.getGender());
        user.setNickname(userDto.getNickName());
        user.setProvince(userDto.getProvince());
        user.setUpdateBy(getCurrentUser());

        userMapper.updateBySelective(user);
    }

    /**
     * 批量更新群昵称
     *
     * @author troytan
     * @date 2018年7月13日
     * @param groupUsers
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.UserService#updateNickname(java.util.List)
     */
    @Override
    public boolean updateNickname(List<GroupUserDto> groupUsers) {
        groupUserMapper.updateBatch(groupUsers);
        return true;
    }

    /**
     * 获取群昵称列表
     *
     * @author troytan
     * @date 2018年7月13日
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.UserService#getGroupUsers()
     */
    @Override
    public List<GroupUserDto> getGroupUsers() {
        return groupUserMapper.listGroupUsers(getCurrentUser());
    }

    @Override
    public List<GroupUserDto> deleteGroupUser(GroupUserDto groupUserDto) {
        groupUserMapper.deleteByPrimaryKey(groupUserDto.getGroupId(), groupUserDto.getOpenId());
        return groupUserMapper.listGroupUsers(getCurrentUser());
    }

}
