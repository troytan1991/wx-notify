package com.troytan.notify.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.troytan.notify.dto.FormDto;
import com.troytan.notify.dto.OauthDto;
import com.troytan.notify.dto.TemplateDataValue;

@Component
public class WechatManager {

    private static final Logger log = LoggerFactory.getLogger(WechatManager.class);

    @Value("${wx.appId}")
    private String              appId;
    @Value("${wx.appSecret}")
    private String              appSecret;
    @Value("${wx.oauthUrl}")
    private String              oauthUrl;

    @Autowired
    private RestTemplate        restTemplate;

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

    /**
     * 发送模版消息
     *
     * @author troytan
     * @date 2018年10月12日
     * @param accessToken
     * @param formDto
     */
    public boolean sendTemplateMsg(String accessToken, FormDto formDto) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        map.put("touser", formDto.getOpenId());
        map.put("template_id", "GYscxvv_NzK5cZlgusRYxktlHWLplw4rgLEpYcnv4o8");
        map.put("page", "/pages/main/main");
        map.put("form_id", formDto.getFormId());
        data.put("keyword1", new TemplateDataValue("六块腹肌30天训练"));
        data.put("keyword2", new TemplateDataValue("第二天"));
        data.put("keyword3", new TemplateDataValue("室内"));
        data.put("keyword4", new TemplateDataValue("30人"));

        map.put("data", data);
        try {
            restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token={1}",
                                       map, String.class, accessToken);
            return true;
        } catch (Exception e) {
            log.error("发送失败{0}", formDto.getFormId());
            return false;
        }
    }
}
