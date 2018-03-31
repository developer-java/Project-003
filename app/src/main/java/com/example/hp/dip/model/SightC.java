package com.example.hp.dip.model;

import com.example.hp.dip.R;
import com.example.hp.dip.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SightC {
    private Long id;
    private String valueRu;
    private String valueKz;
    @JsonIgnore
    private City paternId;
    private String imageUrl;
    private String descriptionRu;
    private String descriptionKz;
    private String workTime;
    private String contact;
    private String addressRu;
    private String addressKz;
    private String price;
    private String ratting;
    private boolean payment;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageUrl() {
        return Util._API_DOWNLOAD_IMAGE_URL +"name="+imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionKz() {
        return descriptionKz;
    }

    public void setDescriptionKz(String descriptionKz) {
        this.descriptionKz = descriptionKz;
    }

    public String getAddressRu() {
        return addressRu;
    }

    public void setAddressRu(String addressRu) {
        this.addressRu = addressRu;
    }

    public String getAddressKz() {
        return addressKz;
    }

    public void setAddressKz(String addressKz) {
        this.addressKz = addressKz;
    }

    public City getPaternId() {
        return paternId;
    }

    public void setPaternId(City paternId) {
        this.paternId = paternId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRatting() {
        return ratting;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
    public enum Category{
        HOTEL(R.string.categoryItem1),
        MUSEI(R.string.categoryItem3);

        private String value;
        private int resId;

        Category(String value) {
            this.value = value;
        }

        Category(int resId) {
            this.resId = resId;
        }

        public String getValue() {
            return value;
        }

        public int getResId() {
            return resId;
        }
    }

}
