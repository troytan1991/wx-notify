package com.troytan.notify.manager;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.troytan.notify.dto.OauthDto;

@Component
public class WechatManager {

    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Value("${wx.oauthUrl}")
    private String oauthUrl;

    /**
     * 根据code请求openId与sessionKey
     *
     * @author troytan
     * @date 2018年7月9日
     * @param code
     * @return
     */
    public OauthDto requestOauth(String code) {
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
        WebTarget target = client.target(oauthUrl).path("").queryParam("appid",
                                                                       appId).queryParam("secret",
                                                                                         appSecret).queryParam("js_code",
                                                                                                               code).queryParam("grant_type",
                                                                                                                                "authorization_code");
        String response = target.request("applicayion/json;utf-8").get(String.class);
        System.out.println(response);
        OauthDto oauthDto = null;
        try {
            oauthDto = new ObjectMapper().readValue(response, OauthDto.class);
        } catch (Exception e) {
            throw new ClientErrorException("请求微信api错误", 400);
        }

        return oauthDto;
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
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
        WebTarget target = client.target("https://api.weixin.qq.com/cgi-bin/token").path("").queryParam("appid",
                                                                                                        appId).queryParam("secret",
                                                                                                                          appSecret).queryParam("grant_type",
                                                                                                                                                "client_credential");
        String response = target.request("applicayion/json;utf-8").get(String.class);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(response);
        return jsonObject.get("access_token").getAsString();
    }
}
