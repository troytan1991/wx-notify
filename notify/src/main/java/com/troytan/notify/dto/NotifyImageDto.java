package com.troytan.notify.dto;

import java.util.List;

import com.troytan.notify.domain.Notify;

public class NotifyImageDto extends Notify {

    private List<String> imageUrls;

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

}
