package com.troytan.notify.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 更新图文
     *
     * @author troytan
     * @throws Exception
     * @date 2018年6月25日
     */
    public void updateNews() {
        String token = getAccessToken();
        // 更新最近十条
        NewsRequestDto in = new NewsRequestDto("news", 0, 10);
        String response = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token={1}",
                                                     in, String.class, token);
        JsonParser jParser = new JsonParser();
        JsonObject object = (JsonObject) jParser.parse(response);
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
        String response = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?appid={1}&secret={2}&grant_type={3}",
                                                    String.class, APP_ID, APP_SECRET, "client_credential");
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(response);
        return jsonObject.get("access_token").getAsString();
    }

    /**
     * 获取素材总数
     *
     * @author troytan
     * @date 2018年10月6日
     * @param token
     * @return
     */
//    private int getTotalCount(String token) {
//        String response = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token={1}",
//                                                    String.class, token);
//        JsonObject jsonObject = (JsonObject) new JsonParser().parse(response);
//        return jsonObject.get("news_count").getAsInt();
//    }

}
