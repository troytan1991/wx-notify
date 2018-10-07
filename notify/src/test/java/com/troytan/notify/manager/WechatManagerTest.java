package com.troytan.notify.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.troytan.notify.dto.OauthDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatManagerTest {

    @Autowired
    WechatManager wechatManager;

    @Test
    public void testGetAccessToken() {
        String code = wechatManager.getAccessToken();
        OauthDto oauthDto = wechatManager.requestOauth(code);
        System.out.println(oauthDto.getOpenid());
    }

}
