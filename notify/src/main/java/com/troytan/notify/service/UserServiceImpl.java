package com.troytan.notify.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.troytan.notify.constant.Constant;
import com.troytan.notify.domain.Form;
import com.troytan.notify.domain.GroupUser;
import com.troytan.notify.domain.Notify;
import com.troytan.notify.domain.User;
import com.troytan.notify.dto.FormDto;
import com.troytan.notify.dto.GroupDto;
import com.troytan.notify.dto.GroupUserDto;
import com.troytan.notify.dto.OauthDto;
import com.troytan.notify.dto.UserDto;
import com.troytan.notify.dto.UserSessionDto;
import com.troytan.notify.manager.WechatManager;
import com.troytan.notify.repository.FormMapper;
import com.troytan.notify.repository.GroupUserMapper;
import com.troytan.notify.repository.NotifyMapper;
import com.troytan.notify.repository.UserMapper;
import com.troytan.notify.util.AESUtils;
import com.troytan.notify.util.SHAUtils;
import com.troytan.notify.util.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger         log        = LoggerFactory.getLogger(UserServiceImpl.class);

    private ThreadLocal<UserSessionDto> userHolder = new ThreadLocal<>();

    @Autowired
    private NotifyMapper                notifyMapper;
    @Autowired
    private GroupUserMapper             groupUserMapper;
    @Autowired
    private UserMapper                  userMapper;
    @Autowired
    private UserService                 userService;
    @Autowired
    private FormMapper                  formMapper;
    @Autowired
    private WechatManager               wechatManager;

    /**
     * 关联群组-通知，群组-用户
     *
     * @author troytan
     * @date 2018年7月10日
     * @param oauthDto
     * @param groupDto
     * @return (non-Javadoc)
     * @throws Exception
     * @see com.troytan.notify.service.UserService#registerGroup(com.troytan.notify.dto.OauthDto,
     * com.troytan.notify.dto.GroupDto)
     */
    @Override
    @Transactional
    public String registerGroup(GroupDto groupDto) throws Exception {
        UserSessionDto sessionDto = userHolder.get();
        // 解密群ID
        String result = AESUtils.decrypt(groupDto.getEncryptedData(), groupDto.getIv(), sessionDto.getSessionKey());
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        String groupId = jsonObject.get("openGId").getAsString();

        // 进行通知-群ID关联
        Notify dbNotify = notifyMapper.selectByPrimaryKey(groupDto.getNotifyId());
        if (dbNotify.getGroupId() == null) {
            dbNotify.setGroupId(groupId);
            notifyMapper.updateByPrimaryKey(dbNotify);
        }

        // 群--用户关联
        GroupUser groupUser = groupUserMapper.selectByGroupAndOpenId(groupId, sessionDto.getOpenId());
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
        userService.putSession(shaKey,
                               new UserSessionDto(user.getUserId(), user.getOpenId(), oauthDto.getSession_key()));

        return shaKey;
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
        user.setNickname(StringUtils.base64Encode(userDto.getNickName()));
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
        return decodeName(groupUserMapper.listGroupUsers(getCurrentUser()));
    }

    @Override
    public List<GroupUserDto> deleteGroupUser(GroupUserDto groupUserDto) {
        groupUserMapper.deleteByPrimaryKey(groupUserDto.getGroupUserId());
        return decodeName(groupUserMapper.listGroupUsers(getCurrentUser()));
    }

    /**
     * 将nickname base64解码
     *
     * @author troytan
     * @date 2018年9月14日
     * @param list
     * @return
     */
    private List<GroupUserDto> decodeName(List<GroupUserDto> list) {
        for (GroupUserDto groupUserDto : list) {
            groupUserDto.setNickname(StringUtils.base64Decode(groupUserDto.getNickname()));
        }
        return list;
    }

    @Override
    public UserDto getUser() {
        UserDto userDto = new UserDto();
        User user = userMapper.selectByPrimaryKey(getCurrentUser());
        userDto.setAvatarUrl(user.getAvatarUrl());
        userDto.setNickName(StringUtils.base64Decode(user.getNickname()));
        userDto.setGender(user.getGender());
        return userDto;
    }

    /**
     * 缓存用户授权信息
     *
     * @author troytan
     * @date 2018年10月14日
     * @param sessionId
     * @param user
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.UserService#putSession(java.lang.String, com.troytan.notify.dto.UserSessionDto)
     */
    @Override
    @CachePut(value = "session", key = "#sessionId")
    public UserSessionDto putSession(String sessionId, UserSessionDto user) {
        return user;
    }

    /**
     * 获取用户授权
     *
     * @author troytan
     * @date 2018年10月14日
     * @param sessionId
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.UserService#getSession(java.lang.String)
     */
    @Override
    @Cacheable(value = "session", key = "#sessionId")
    public UserSessionDto getSession(String sessionId) {
        return null;
    }

    @Override
    public int notifyUser() {
        List<FormDto> formDtos = formMapper.listFormId();
        String accessToken = wechatManager.getAccessToken();
        // 遍历发送消息
        return formDtos.parallelStream().mapToInt(formDto -> {
            if (wechatManager.sendTemplateMsg(accessToken, formDto)) {
                // 清除已使用的formId
                formMapper.deleteByPrimaryKey(formDto.getId());
                return 1;
            }
            return 0;
        }).sum();
    }

    @Override
    public void uploadFormIds(List<String> formIds) {
        Integer userId = userService.getCurrentUser();
        for (String formId : formIds) {
            Form form = new Form();
            form.setCreateBy(userId);
            form.setFormId(formId);
            form.setUserId(userId);
            try {
                formMapper.insert(form);
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
    }

}
