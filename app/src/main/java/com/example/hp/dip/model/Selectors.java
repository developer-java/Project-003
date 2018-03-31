package com.example.hp.dip.model;

import com.example.hp.dip.R;
import com.example.hp.dip.activity.Selector;

public enum Selectors {
    CITY(R.string.selector1),
    SANATORIUM(R.string.selector2),
    REST_ZONE(R.string.selector3),
    HISTORICAL_OBJECTS(R.string.selector4);
    private int resID;

    Selectors(int resID) {
        this.resID = resID;
    }

    public int getResID() {
        return resID;
    }
}
