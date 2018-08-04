package com.troytan.notify.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {

    /**
     * 提取html中的文本
     *
     * @author troytan
     * @date 2018年6月25日
     * @param html html字符串
     * @param maxLength 提取的最大文本长度
     * @return
     */
    public static String getTextFromHtml(String html, int maxLength) {
        String pattern = "<.*?>([\\w[\\u4E00-\\u9FA5]]+)<.*?>";// 匹配所有的标签内容，并且是字符/中文类型
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(html);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group(1));
        }
        if (maxLength > sb.length()) {
            return sb.toString();
        }

        return sb.substring(0, maxLength) + "...";
    }

}
