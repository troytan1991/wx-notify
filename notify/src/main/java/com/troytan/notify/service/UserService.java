package com.troytan.notify.service;

import java.security.NoSuchAlgorithmException;

import com.troytan.notify.dto.GroupDto;
import com.troytan.notify.dto.OauthDto;
import com.troytan.notify.dto.UserDto;
import com.troytan.notify.dto.UserSessionDto;

public interface UserService {

    String registerGroup(GroupDto groupDto);

    String logUser(OauthDto oauthDto) throws NoSuchAlgorithmException;
    
    Integer getCurrentUser();

    UserSessionDto checkSessionId(String sessionId);

    void updateUser(UserDto userDto);

    void setCurrentUser(UserSessionDto userSessionDto);
}
