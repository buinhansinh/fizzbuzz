package com.flystar.service.utils;

import java.util.Collection;

/**
 * Created by zack on 8/12/2016.
 */
public class Utils {

    public static boolean isNullOrEmpty(String s){
        if(s == null || s.length() < 1) return true;
        return false;
    }

    public static boolean isEmpty(Collection collection){
        if(collection == null || collection.isEmpty()) return true;
        return false;
    }
}
