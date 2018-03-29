package com.example.hp.dip.util;
import java.util.Locale;
import java.util.Random;

/**
 * Тут хранятся статические данные
 */
public class Util {
    public static final String _API_URL = "http://192.168.0.103:8087/rest";
    public static String[] COLORS = new String[]{"#EF5350", "#EC407A", "#AB47BC", "#673AB7", "#3F51B5", "#2196F3", "#0288D1", "#0097A7", "#009688", "#7CB342", "#FDD835", "#FF9800", "#FF5722"};
    public static final String _API_DOWNLOAD_IMAGE_URL = _API_URL + "/download?";
    public static final String _API_REGION_LIST_URL = _API_URL + "/dip/regions";
    public static final String _API_SIGHT_LIST_URL = _API_URL + "/dip/sight/";
    public static final String _API_SIGHT_CATEGORY_URL = _API_URL + "/dip/sight/category/";
    public static int DRAW_SELECTED_ITEM=0;

    public static String getRandomColor(){
        int pos = (new Random().nextInt(12));
        return COLORS[pos];
    }

    public static String getStaticColor(){
        return "#4FC3F7";
    }

    public static boolean getLangIsRu(){
        String lang = Locale.getDefault().getDisplayLanguage();
        if(lang.toLowerCase().equals("русский")){
            return true;
        }else {
            return false;
        }
    }
}
