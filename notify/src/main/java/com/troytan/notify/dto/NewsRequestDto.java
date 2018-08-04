package com.troytan.notify.dto;

/**
 * 请求新闻列表的body
 * 
 * @author troytan
 * @date 2018年6月25日
 */
public class NewsRequestDto {

    private String  type;
    private Integer offset;
    private Integer count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public NewsRequestDto(String type, Integer offset, Integer count){
        super();
        this.type = type;
        this.offset = offset;
        this.count = count;
    }

}
