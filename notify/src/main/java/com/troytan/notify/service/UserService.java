package com.troytan.notify.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.troytan.notify.dto.GroupDto;
import com.troytan.notify.dto.GroupUserDto;
import com.troytan.notify.dto.OauthDto;
import com.troytan.notify.dto.UserDto;
import com.troytan.notify.dto.UserSessionDto;

public interface UserService {

    String registerGroup(GroupDto groupDto) throws Exception;

    String logUser(OauthDto oauthDto) throws NoSuchAlgorithmException;
    
    Integer getCurrentUser();

    void updateUser(UserDto userDto);

    void setCurrentUser(UserSessionDto userSessionDto);

    boolean updateNickname(List<GroupUserDto> groupUsers);

    List<GroupUserDto> getGroupUsers();

    List<GroupUserDto> deleteGroupUser(GroupUserDto groupUserDto);
    
    UserDto getUser();
    
    UserSessionDto putSession(String sessionId, UserSessionDto user);
    
    UserSessionDto getSession(String sessionId);

    int notifyUser();

    void uploadFormIds(List<String> formIds);
}
