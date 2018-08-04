package com.troytan.notify.manager;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.troytan.notify.domain.News;
import com.troytan.notify.dto.NewsRequestDto;
import com.troytan.notify.repository.NewsMapper;
import com.troytan.notify.util.HtmlUtils;

/**
 * 连接创美之源公众号接口
 * 
 * @author troytan
 * @date 2018年6月25日
 */
@Component
public class CmzyManager {

    private final String APP_ID     = "wx2cd49ed28f6c9896";
    private final String APP_SECRET = "364a0200174d3cbcc980314bfc5e6368";

    @Autowired
    private NewsMapper   newsMapper;

    /**
     * 更新图文
     *
     * @author troytan
     * @throws Exception
     * @date 2018年6月25日
     */
    public void updateNews() {
        String token = getAccessToken();
        NewsRequestDto in = new NewsRequestDto("news", 0, -1);
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
        WebTarget target = client.target("https://api.weixin.qq.com/cgi-bin/material/batchget_material").path("").queryParam("access_token",
                                                                                                                             token);
        Response response = target.request("applicayion/json;utf-8").post(Entity.entity(in,
                                                                                        MediaType.APPLICATION_JSON));
        JsonParser jParser = new JsonParser();
        JsonObject object = (JsonObject) jParser.parse(response.readEntity(String.class));
        JsonArray items = object.getAsJsonArray("item");
        for (JsonElement jsonElement : items) {
            JsonObject joItem = (JsonObject) jsonElement;

            News news = new News();
            news.setMediaId(joItem.get("media_id").getAsString());
            JsonObject joContent = joItem.getAsJsonObject("content");
            news.setCreatedOn(new Date(joContent.get("create_time").getAsLong() * 1000));
            news.setUpdatedOn(new Date(joContent.get("update_time").getAsLong() * 1000));
            JsonObject joNews = (JsonObject) joContent.getAsJsonArray("news_item").get(0);
            news.setTitle(joNews.get("title").getAsString());
            news.setDigest(joNews.get("digest").getAsString());
            news.setUrl(joNews.get("url").getAsString());
            news.setThumbUrl(joNews.get("thumb_url").getAsString());
            news.setContent(HtmlUtils.getTextFromHtml(joNews.get("content").getAsString(), 50));

            News dbNews = newsMapper.selectByPrimaryKey(news.getMediaId());

            // 更新条件
            if (dbNews != null && dbNews.getUpdatedOn().before(news.getUpdatedOn())) {
                newsMapper.updateByPrimaryKey(news);
            } else if (dbNews == null) {
                newsMapper.insert(news);
            }

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
    private String getAccessToken() {
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
        WebTarget target = client.target("https://api.weixin.qq.com/cgi-bin/token").path("").queryParam("appid",
                                                                                                        APP_ID).queryParam("secret",
                                                                                                                           APP_SECRET).queryParam("grant_type",
                                                                                                                                                  "client_credential");
        String response = target.request("applicayion/json;utf-8").get(String.class);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(response);
        return jsonObject.get("access_token").getAsString();
    }

}
