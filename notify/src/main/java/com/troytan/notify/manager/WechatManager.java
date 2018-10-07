package com.troytan.notify.manager;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.troytan.notify.dto.OauthDto;

@Component
public class WechatManager {

    @Value("${wx.appId}")
    private String       appId;
    @Value("${wx.appSecret}")
    private String       appSecret;
    @Value("${wx.oauthUrl}")
    private String       oauthUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据code请求openId与sessionKey
     *
     * @author troytan
     * @date 2018年7月9日
     * @param code
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public OauthDto requestOauth(String code) {

        String response = restTemplate.getForObject(oauthUrl + "?appid={1}&secret={2}&js_code={3}&grant_type={4}",
                                                    String.class, appId, appSecret, code, "authorization_code");
        try {
            return new ObjectMapper().readValue(response, OauthDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取token
     *
     * @author troytan
     * @date 2018年6月25日
     * @return
     * @throws Exception
     */
    public String getAccessToken() {
        String response = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?appid={1}&secret={2}&grant_type={3}",
                                                    String.class, appId, appSecret, "client_credential");
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(response);
        return jsonObject.get("access_token").getAsString();
    }
}
