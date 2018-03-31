package com.example.hp.dip.model;

import com.example.hp.dip.R;

public enum SelectorsCity {
    HOTEL(R.string.categoryItem1),
    MUSEI(R.string.categoryItem3),
    RESTORAN(R.string.categoryItem2);
    private String value;
    private int resId;

    SelectorsCity(String value) {
        this.value = value;
    }

    SelectorsCity(int resId) {
        this.resId = resId;
    }

    public String getValue() {
        return value;
    }

    public int getResId() {
        return resId;
    }
}
