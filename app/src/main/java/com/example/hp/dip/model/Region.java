package com.example.hp.dip.model;

import com.example.hp.dip.util.Util;

public class Region {
    private Long id;
    private String valueRu;
    private String valueKz;
    private String code;
    private String imageUrl;
    private Long countSight;

    public String getImageUrl() {
        return Util._API_DOWNLOAD_IMAGE_URL +"type=region&id="+getId();
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCountSight() {
        return countSight;
    }

    public void setCountSight(Long countSight) {
        this.countSight = countSight;
    }

    public void setImageURL(String imageURL) {
        this.imageUrl = imageURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id= id;
    }

    public String getValueRu() {
        return valueRu;
    }

    public void setValueRu(String valueRu) {
        this.valueRu = valueRu;
    }

    public String getValueKz() {
        return valueKz;
    }

    public void setValueKz(String valueKz) {
        this.valueKz = valueKz;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
